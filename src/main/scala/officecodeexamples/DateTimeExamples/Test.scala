package officecodeexamples.DateTimeExamples

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, DateTimeZone, LocalDate, LocalTime}

object jj extends App {

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
  }

  val index = 1506092400000L
  val lastProcessedDateTimeInUTC = new DateTime(index, DateTimeZone.UTC)
  val lastProcessedDateInLocalZone = convertToLocalZone(lastProcessedDateTimeInUTC.getMillis)
  val nowInLocalZone = convertToLocalZone(DateTime.now.getMillis)
  val startDateInLocalZone = lastProcessedDateInLocalZone
  val localDayEnd = lastProcessedDateInLocalZone.toDateTimeAtEndOfDay
  val endDateInLocalZone = if (lastProcessedDateInLocalZone.toDateTimeAtEndOfDay.isAfter(nowInLocalZone)) {
    nowInLocalZone
  } else lastProcessedDateInLocalZone.toDateTimeAtEndOfDay
  val searchDat1 = convertToUTC(startDateInLocalZone.getMillis)
  val searchDat2 = convertToUTC(endDateInLocalZone.getMillis)
  println(searchDat1 + " " + searchDat2)

  val endDateInUTC = {
    if (lastProcessedDateTimeInUTC.toLocalDate equals LocalDate.now) {
      // updating for the same day. Do not update the zk index with the end of day value.
      //lastProcessedDateTimeInUTC //.plusSeconds(1)
      DateTime.now
    } else {
      lastProcessedDateTimeInUTC.toDateTimeAtEndOfDay.plusSeconds(1)
    }
  }

  println(DateTime.now)
  println(DateTime.now.getMillis)
  println(convertToLocalZone(DateTime.now().getMillis))
  println(convertToUTC(DateTime.now().getMillis))

  println(">>>>>" + new DateTime(DateTime.now.getMillis, DateTimeZone.UTC))
  println("ss" + new DateTime(DateTime.now.getMillis, DateTimeZone.forID("Asia/Kolkata")))


}