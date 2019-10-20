package spark


import org.apache.spark.sql.SparkSession
import spark.sparkbook.SchemaSortTest.customSchema

object DateNormalizerTest extends App {

  val list = List("date1")

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val df = spark.read.format("csv").
    option("header", "true").
    option("delimiter", "\t").
    option("timestampFormat", "yyyy/MM/dd HH:mm:ss")
    .schema(customSchema).load("E:/datenormtest.csv")

  val resDF = new DateNormalizer("MM-YYYY-DD", list).normalizeDate(df)

  resDF.show(false)
}

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions._
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try

class DateNormalizer(format: String, colList: List[String]) {

  private val logger: Logger = LoggerFactory.getLogger(this.getClass())
  private final lazy val defaultDate: String = getDefaultDate

  private def getDefaultDate: String = {
    if (format.contains("/")) buildDefaultDate("/")
    else if (format.contains("\\")) buildDefaultDate("\\")
    else if (format.contains("-")) buildDefaultDate("-")
    else throw new Exception(s"In-correct separator in date format: $format")
  }

  private def buildDefaultDate(separator: String): String = {
    val dateSplitArray = format.split(separator)
    if (dateSplitArray.length != 3) {
      throw new Exception(s"error splitting default date-format: $format")
    }
    if (dateSplitArray(0).length == 4) s"1753${separator}01${separator}01"
    else if (dateSplitArray(1).length == 4) s"01${separator}1753${separator}01"
    else s"01${separator}01${separator}1753"
  }

  def normalizeDate(dataFrame: DataFrame): DataFrame = {

    logger.info(s"Normalizing date for cols: ${colList.mkString(",")}")
    var d_f = dataFrame
    for (col <- colList) {
      d_f = formatDate(col, format, d_f)
    }
    d_f
  }

  def formatDate(columnName: String, format: String, dataFrame: DataFrame): DataFrame = {
    var newDF = dataFrame

    newDF = newDF.withColumn(columnName, DateNormalizerUDCs.extractDate(col({
      columnName
    }), lit(defaultDate)))

    //C1, C2, C3 filled with day,month and year numbers e.g 12 10 2006
    newDF = newDF.withColumn("C1", DateNormalizerUDCs.splitDate(col({
      columnName

    }), lit(defaultDate), lit(0), lit("<"), lit(2)))


    newDF = newDF.withColumn("C2", DateNormalizerUDCs.splitDate(col({
      columnName
    }), lit(defaultDate), lit(1), lit("<"), lit(2)))

    newDF = newDF.withColumn("C3", DateNormalizerUDCs.splitDate(col({
      columnName
    }), lit(defaultDate), lit(0), lit(">"), lit(4)))


    //assuming c1 has some value > 12, means it's a day column else we assume its a month column
    val isC1Day = if (newDF.select("C1").where(col("C1") >= lit(12)).head(1).isEmpty) true else false

    newDF = if (isC1Day)
      newDF.withColumn(columnName, DateNormalizerUDCs.buildDate(col({
        "C1"
      }), col({
        "C2"
      }), col({
        "C3"
      }), lit(format), lit(defaultDate), col(columnName)))
    else
      newDF.withColumn(columnName, DateNormalizerUDCs.buildDate(col({
        "C2"
      }), col({
        "C1"
      }), col({
        "C3"
      }), lit(format), lit(defaultDate), col(columnName)))

    newDF.drop(List("C1", "C2", "C3"): _*)
  }

}

object DateNormalizerUDCs {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  val extractDate: UserDefinedFunction = udf(f = (date: String, defaultDate: String) => {

    if (date == null || date.isEmpty) "" else {
      if (date.trim.length > 8) {
        val extractedDateTry = Try(date.take(10))
        if (extractedDateTry.isSuccess) {
          val dateStr = extractedDateTry.get
          if (dateStr.contains("/") || dateStr.contains("\\") || dateStr.contains("-")) dateStr else {
            val msg = s"date: $date, doesn't contain / , \\, - separators, setting default date value: $defaultDate"
            val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
            logger.info(s"On line number: $lineNumber, $msg")
            defaultDate
          }
        } else {
          val msg = s"Unable to extract date, for date:$date, setting default date value: $defaultDate"
          val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
          logger.info(s"On line number: $lineNumber, $msg")
          defaultDate
        }
      } else date
    }
  })
  /**
    * arrayIndex: for day, month, its 0, 1 means after splitting take first day, next time month as we assumed we took day in first iteration for 0.
    * maxSplitSize: for day, month, its 2 after splitting and for year its 4
    */
  val splitDate: UserDefinedFunction = udf((date: String, defaultDate: String, arrayIndex: Int, condition: String, maxSplitSize: Int) => {
    if (date == null || date.isEmpty) date else {
      if (date.contains("/")) {
        splitDateTry(date, 4, arrayIndex, "/", defaultDate, condition, maxSplitSize)
      } else if (date.contains("\\")) {
        splitDateTry(date, 4, arrayIndex, "\\", defaultDate, condition, maxSplitSize)
      } else if (date.contains("-")) {
        splitDateTry(date, 4, arrayIndex, "-", defaultDate, condition, maxSplitSize)
      } else if (date.length <= 8) {
        splitDateTry(date, 4, arrayIndex, "/", defaultDate, condition, maxSplitSize)
      } else {
        val msg = s"date: $date doesn't contain expected separators(/, \\, -), setting default date value: $defaultDate"
        val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
        logger.info(s"On line number: $lineNumber, $msg")
        defaultDate
      }
    }
  })

  val buildDate: UserDefinedFunction = udf((day: String, month: String, year: String, format: String, defaultDate: String, currentColumnValue: String) => {
    if ((day == null || day.isEmpty) || (month == null || month.isEmpty) || (year == null || year.isEmpty)) "" else {

      if (currentColumnValue.length <= 8) {
        s"$year-$month-$day"
      } else {
        if (format.contains("/")) {
          if (format.startsWith("DD") && format.endsWith("YYYY")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$day/$month/$year"
          }
          else if (format.startsWith("MM") && format.endsWith("YYYY")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$month/$day/$year"
          }
          else if (format.startsWith("YYYY") && format.endsWith("MM")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$year/$day/$month"
          }
          else if (format.startsWith("YYYY") && format.endsWith("DD")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else {
              val x = s"$year/$month/$day"
              x
            }
          }
          else if (format.startsWith("DD") && format.endsWith("MM")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$day/$year/$month"
          }
          else {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$month/$year/$day"
          }
        } else if (format.contains("\\")) {
          if (format.startsWith("DD") && format.endsWith("YYYY")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$day\\$month\\$year"
          }
          else if (format.startsWith("MM") && format.endsWith("YYYY")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$month\\$day\\$year"
          }
          else if (format.startsWith("YYYY") && format.endsWith("MM")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$year\\$day\\$month"
          }
          else if (format.startsWith("YYYY") && format.endsWith("DD")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$year\\$month\\$day"
          }
          else if (format.startsWith("DD") && format.endsWith("MM")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$day\\$year\\$month"
          }
          else {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$month/$year/$day"
          }
        } else if (format.contains("-")) {
          if (format.startsWith("DD") && format.endsWith("YYYY")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$day-$month-$year"
          }
          else if (format.startsWith("MM") && format.endsWith("YYYY")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$month-$day-$year"
          }
          else if (format.startsWith("YYYY") && format.endsWith("MM")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$year-$day-$month"
          }
          else if (format.startsWith("YYYY") && format.endsWith("DD")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$year-$month-$day"
          }
          else if (format.startsWith("DD") && format.endsWith("MM")) {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$day-$year-$month"
          }
          else {
            if (day.equals(defaultDate) || month.equals(defaultDate) || year.equals(defaultDate)) defaultDate else s"$month/$year/$day"
          }
        } else {
          val msg = s"$format missing default separators(/, \\, -), setting default date value: $defaultDate"
          val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
          logger.info(s"On line number: $lineNumber, $msg")
          defaultDate
        }
      }

    }
  }
  )


  private def splitDateTry(dateString: String, defaultLength: Int, arrayIndex: Int, separator: String, defaultDate: String, condition: String, maxSize: Int): String = {

    if (dateString.length > 8) {
      val tryResult = Try {
        val datesArray = dateString.split(separator)
        if (condition == "<") {
          val filterResult = datesArray.filter(_.length < defaultLength)(arrayIndex)
          if (filterResult.length == 1) "0" + filterResult else filterResult
        } else
          datesArray.filter(_.length >= defaultLength)(arrayIndex)
      }
      if (tryResult.isSuccess) {
        if (tryResult.get.trim.length != maxSize) {
          val msg = s"split result is not equal to maxSize"
          val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
          logger.info(s"On line number: $lineNumber, $msg")
          defaultDate
        } else {
          tryResult.get
        }
      } else {
        val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
        val msg = s"Couldn't split $dateString with separator:$separator, so setting it to default date: $defaultDate"
        logger.info(s"On line number: $lineNumber, $msg")
        defaultDate
      }
    } else {
      if (dateString.trim.length == 8) {
        if (arrayIndex == 0 && maxSize == 2) {
          dateString.takeRight(2) //takes day of YYYYMMDD, for C1
        } else if (arrayIndex == 1 && maxSize == 2) {
          dateString.slice(2, 4) ////takes month of YYYYMMDD, for C2
        } else if (maxSize == 4) {
          dateString.take(4) // take year of YYYYMMDD, for C3
        } else dateString
      } else if (dateString.length == 6) {
        if (arrayIndex == 0 && maxSize == 2) {
          "00" //takes day of YYYYDD, for C1
        } else if (arrayIndex == 1 && maxSize == 2) {
          dateString.takeRight(2) ////takes month of YYYYMM, for C2
        } else if (maxSize == 4) {
          dateString.take(4) // take year of YYYYMM or YYYYDD, for C3
        } else dateString
      } else {
        val lineNumber = this.getClass.getSimpleName + ":" + new Exception().getStackTrace()(0).getLineNumber
        val msg = s"Couldn't split $dateString without separator, so setting it to default date: $defaultDate"
        logger.info(s"On line number: $lineNumber, $msg")
        defaultDate
      }


    }

  }

}