package DateTimeExamples

import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.DateTimeFormat

object ConvertAMPMLocalDtToUTCTest1 extends App {
  def getDateTimeFromStringAMPM(inputDateTime: String): DateTime = {
    val pattern = "MM/dd/yyyy hh:mm:ss a"
    val timeZoneStr = "Asia/Kolkata"

    val timeZone = DateTimeZone.forID(timeZoneStr)

    val formatter = DateTimeFormat.forPattern(pattern)
    val dateTime = formatter.withZone(timeZone).parseDateTime(inputDateTime)
    dateTime.withZone(DateTimeZone.UTC)
  }
  val inputStr = "2/15/2018 1:30:22 PM"
  println{ getDateTimeFromStringAMPM(inputStr) }


}