package DateTimeExamples

import org.joda.time.{LocalTime, Minutes, Period}

object TimeDiff1 extends App {
  val time1 = LocalTime.parse("08:20:00.000")
  val time2 = LocalTime.parse("10:00:00.000")
  val timeDiff = Minutes.minutesBetween(time1, time2).getMinutes * 60000
  val period = new Period(timeDiff)
  s"Hours-${period.getHours} Minutes-${period.getMinutes} Seconds-${period.getSeconds}"
}