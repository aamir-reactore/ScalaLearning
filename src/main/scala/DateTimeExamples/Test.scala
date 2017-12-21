package DateTimeExamples

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, DateTimeZone, LocalDate, LocalTime}

object jjj extends App {

  def convertToUTC(dateCreatedInLong: Long) = {
    val clientTime = new DateTime(dateCreatedInLong).withZoneRetainFields(DateTimeZone.forID("Asia/Kolkata"))
    val utcTime = clientTime.withZone(DateTimeZone.UTC)
    val fmtTP = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val strTP = fmtTP.print(utcTime)
    DateTime.parse(strTP)
  }

  def convertToLocalZone(dateCreatedInLong: Long) = {
    val utcTime: DateTime = new DateTime(dateCreatedInLong).withZoneRetainFields(DateTimeZone.UTC)
    val clientTime = utcTime.withZone(DateTimeZone.forID("Asia/Kolkata"))
    val fmtTP = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val strTP = fmtTP.print(clientTime)
    DateTime.parse(strTP)

  }

  implicit class JodaDateTimeExtensions(val dateTime: DateTime) {
    def toDateTimeAtEndOfDay = {
      dateTime.withTime(LocalTime.parse("23:59:59.999"))
    }

    def toUTCFormat = {
      dateTime.toString("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }
  }

/*

  val index = 1506085200000L
  //val lastProcessedDateTimeInUTC = new DateTime(index, DateTimeZone.UTC)
  val lastProcessedDateInLocalZone = convertToLocalZone(index)
  val nowInLocalZone = convertToLocalZone(DateTime.now.getMillis)
  val startDateInLocalZone = lastProcessedDateInLocalZone
  val localDayEnd = lastProcessedDateInLocalZone.toDateTimeAtEndOfDay
  val endDateInLocalZone = if (lastProcessedDateInLocalZone.toDateTimeAtEndOfDay.isAfter(nowInLocalZone)) {
    nowInLocalZone
  } else lastProcessedDateInLocalZone.toDateTimeAtEndOfDay
  val searchDat1 = convertToUTC(startDateInLocalZone.getMillis).toUTCFormat
  val searchDat2 = convertToUTC(endDateInLocalZone.getMillis).toUTCFormat
  println(searchDat1 + " " + searchDat2)*/



 println("*******")
  println(DateTime.now)
  println(DateTime.now.getMillis)
  println(convertToLocalZone(DateTime.now().getMillis))
  println(convertToUTC(DateTime.now().getMillis))

  println(">>>>>" + new DateTime(1506051000000l, DateTimeZone.UTC))
  println("ss" + new DateTime(DateTime.now.getMillis, DateTimeZone.forID("Asia/Kolkata")))


}