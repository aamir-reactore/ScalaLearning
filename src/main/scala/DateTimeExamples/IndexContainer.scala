package DateTimeExamples

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, DateTimeZone, LocalTime}
import DateTimeExt.JodaDateTimeExtensions

case class MyIndexDateTimeContainer(indexUTC: DateTime) {

  val indexLocalDateTime = DateTimeHelper.convertToLocalZone(indexUTC.getMillis)
  val startOfMonthLocal  = indexLocalDateTime.withDayOfMonth(1).withTimeAtStartOfDay
  val endOfMonthLocal    = startOfMonthLocal.plusMonths(1).minusMillis(1)

  val startOfMonthUTC = DateTimeHelper.convertToUTC(startOfMonthLocal.getMillis)
  //.toUTCFormat
  val endOFMonthUTC   = DateTimeHelper.convertToUTC(endOfMonthLocal.getMillis) //.toUTCFormat

  val startOfDayLocal = indexLocalDateTime.withTimeAtStartOfDay()
  val endOfDayLocal   = startOfDayLocal.plusDays(1).minusMillis(1)

  val startOfDayUTC = DateTimeHelper.convertToUTC(startOfDayLocal.getMillis)
  val endOfDayUTC   = DateTimeHelper.convertToUTC(endOfDayLocal.getMillis)

  val nextDateIndex = startOfDayUTC.plusDays(1)

  def startOfMonthStr = startOfMonthUTC.toUTCFormat

  def endOfMonthStr = endOFMonthUTC.toUTCFormat

  def startOfDayStr = startOfDayUTC.toUTCFormat

  def endOfDayStr = endOfDayUTC.toUTCFormat
}

object DateTimeHelper {
  def convertToLocalZone(dateCreatedInLong: Long) = {
    val utcTime: DateTime = new DateTime(dateCreatedInLong).withZoneRetainFields(DateTimeZone.UTC)
    val clientTime = utcTime.withZone(DateTimeZone.forID("Asia/Kolkata"))
    val fmtTP = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val strTP = fmtTP.print(clientTime)
    DateTime.parse(strTP)

  }
  def convertToUTC(dateCreatedInLong: Long) = {
    val clientTime = new DateTime(dateCreatedInLong).withZoneRetainFields(DateTimeZone.forID("Asia/Kolkata"))
    val utcTime = clientTime.withZone(DateTimeZone.UTC)
    val fmtTP = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val strTP = fmtTP.print(utcTime)
    DateTime.parse(strTP)
  }
}

object ll extends App {

  val dt1 = DateTime.now()
  val dt2 = DateTime.now().plusMinutes(5)

  println(dt1)
  println(dt2)
  val value1:Long = dt1.getMillis
  val value2:Long = dt2.getMillis
  val value3 = (value1 + Math.random * (value2 - value1)).toLong
  println(new DateTime(value3))
}

object DateTimeExt {

  implicit class JodaDateTimeExtensions(val dateTime: DateTime) {

    def toDateTimeAtEndOfDay = {
      dateTime.withTime(LocalTime.parse("23:59:59.999"))
    }

    def toUTCFormat = {
      dateTime.toString("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }

    def toDispatchFormat = {
      dateTime.toString("yyyy/MM/dd'T'HH:mm:ss'Z'")
    }

    /**
      * Format DateTime with the client dateTime format
      */
  /*  def formatDateTime = {
      val format =
        ConfigFactoryHelper.config.getString("out.formats.dateTimeFormat")
      dateTime.toString(format)
    }*/

    //    def formatFuelDate = {
    //      val format = ConfigFactoryHelper.config.getString("out.fuel.formats.date")
    //      dateTime.toString(format)
    //    }

  }

}