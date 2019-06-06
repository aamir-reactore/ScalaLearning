import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
object DateConversion extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(

    Row(1, "2013-11-06T00:00:00.000+01:00"),
    Row(2, "2014/01/12"),
    Row(3, "12/01/2007"),
    Row(4, "02-02-2012T00:00:00.000+01:00"),
    Row(5, "2014-12-10T00:00:00.000+01:00"),
    Row(6, null),
    Row(7, "")
  )

  val someSchema = List(
    StructField("id", IntegerType, true),
    StructField("date", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  val dateSplit = udf((date: String) => {
    date.take(10)
  })

  val f1 = udf((date: String) => {
    if (date.contains("/")) {
      val datesArray = date.split("/")
      datesArray.filter(_.length < 4)(0)
    } else if (date.contains("\\\\")) {
      val datesArray = date.split("\\\\")
      datesArray.filter(_.length < 4)(0)
    } else if (date.contains("-")) {
      val datesArray = date.split("-")
      datesArray.filter(_.length < 4)(0)
    } else {
      throw new Exception("Invalid date format")
    }
  })

  val f2 = udf((date: String) => {
    if (date.contains("/")) {
      val datesArray = date.split("/")
      datesArray.filter(_.length < 4)(1)
    } else if (date.contains("\\\\")) {
      val datesArray = date.split("\\\\")
      datesArray.filter(_.length < 4)(1)
    } else if (date.contains("-")) {
      val datesArray = date.split("-")
      datesArray.filter(_.length < 4)(1)
    } else {
      throw new Exception("Invalid date format")
    }
  })

  val f3 = udf((date: String) => {
    if (date.contains("/")) {
      val datesArray = date.split("/")
      datesArray.filter(_.length >= 4)(0)
    } else if (date.contains("\\\\")) {
      val datesArray = date.split("\\\\")
      datesArray.filter(_.length >= 4)(0)
    } else if (date.contains("-")) {
      val datesArray = date.split("-")
      datesArray.filter(_.length >= 4)(0)
    } else {
      throw new Exception("Invalid date format")
    }
  })

  //var newDF = df.withColumn("date", df("date").cast(DateType))

  var newDF = df.withColumn("date", dateSplit(col({
    "date"
  })))

  newDF = newDF.withColumn("C1", f1(col({
    "date"
  })))
  newDF = newDF.withColumn("C2", f2(col({
    "date"
  })))
  newDF = newDF.withColumn("C3", f3(col({
    "date"
  })))

  newDF.show(false)








  /*  def toDate(col: Column) = {
      val formats: Seq[String] = Seq("MM/dd/yyyy", "yyyy-MM-dd")
      coalesce(formats.map(f => to_date(col, f)): _*)
    }

    var resDf = df.withColumn("pdt", toDate(col("date")))
    resDf.show()
    resDf = resDf.withColumn("newCOl", df("date"))
    resDf.show()*/
}