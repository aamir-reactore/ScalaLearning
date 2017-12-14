package DateTimeExamples


import java.time.LocalTime

import org.joda.time.{DateTime, Hours, Period}

import scala.concurrent.duration._

case class DateRange(startTS: DateTime, endTS: DateTime)  {
  def getNoOfHoursBetween: Double = {
    val millis = endTS.getMillis - startTS.getMillis
    millis.milliseconds.toMinutes.toDouble / 60D
  }

  def getNoOfMinutesBetween: Double = {
    val millis = endTS.getMillis - startTS.getMillis
    millis.milliseconds.toMinutes.toDouble
  }
}

/**
  * Find Hours between two DateTime Ranges
  */
object DateTimeRangeTest1 extends App {
  val dr11=DateRange(DateTime.parse("2016-12-01T08:00:00.000Z"),DateTime.parse("2016-12-01T09:40:00.000Z"))
  val hours = Hours.hoursBetween(dr11.startTS,dr11.endTS).getHours
  println(s"Hours between DateTime range = $hours")

  val dr12=DateRange(DateTime.parse("2016-12-01T08:00:00.000Z"),DateTime.parse("2016-12-01T09:40:00.000Z"))
  val millisDiff = dr12.endTS.getMillis - dr12.startTS.getMillis
  val seconds = millisDiff / 1000
  val minutes = seconds / 60
  val hour = minutes / 60
  println(s"seconds $seconds, minutes $minutes, hour $hour")
}
/**
  * Convert minutes to Hours,minutes and seconds
  */
object DateTimeRangeTest2 extends App {
  val minutes = 100 //(1 hour and 40 minutes)
  val timeDiff = minutes * 60000
  val period = new Period(timeDiff)
  println(s"Hours-${period.getHours} Minutes-${period.getMinutes} Seconds-${period.getSeconds}")
}

/**
  * Find LocalTime hours, minutes and seconds
  */
object DateTimeRangeTest3 extends App {
  val localTime: LocalTime = LocalTime.parse("08:20:00.000")
  println(s"hours ${localTime.getHour}")
  println(s"minutes ${localTime.getMinute}")
  println(s"seconds ${localTime.getSecond}")

}