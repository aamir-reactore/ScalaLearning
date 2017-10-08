package eciffocodeexamples.DateTimeExamples

import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.DateTimeFormat

/**
  * Convert a string to DateTime with defined formatter
  */
object StringToDateTimeTest1 extends App {
  val dateTimeString = "2016-12-01 08:00"
  val localDateTimeFormat = "YY-mm-DD HH:mm"

  val formatter = DateTimeFormat.forPattern(localDateTimeFormat)
  val jodaDateTime = DateTime.parse(dateTimeString, formatter)
  println(s"DateTime = $jodaDateTime") //2016-01-01T08:00:00.000+05:30 (UTC format) means will add 5.30 hours
}
/**
  *Convert a string to DateTime but don't allow it to convert to UTC, retain to some local-zone
  */
object StringToDateTimeTest2 extends App {
  val dateTimeString = "2016-12-01 08:00"
  val localDateTimeFormat = "YY-mm-DD HH:mm"
  val localZone = "Asia/Kolkata"

  val formatter = DateTimeFormat.forPattern(localDateTimeFormat)
  val jodaDateTime = DateTime.parse(dateTimeString, formatter)
  val retainDateTime = jodaDateTime.withZoneRetainFields(DateTimeZone.forID(localZone))
  println(s"DateTime = $jodaDateTime") //2016-01-01T08:00:00.000+05:30 (UTC format)
  println(s"retainDateTime = $retainDateTime") // same as above output
  println(retainDateTime.toDateTime(DateTimeZone.UTC)) // reduces 5.30 hours to a local zone.
}
