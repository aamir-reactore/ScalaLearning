import org.joda.time.format.DateTimeFormat
import org.joda.time._

val dt1 = DateTime.parse("2016-12-01T08:00:00.000Z")

val dt2 = DateTime.parse("2016-12-01T09:00:00.000Z")

val  p = new Period(dt1, dt2)
val  hours = p.getHours


val format = DateTimeFormat.forPattern("HH:mm:ss")
val myDate = LocalTime.parse("10:20:00",format)
myDate.toString





import org.joda.time.LocalTime
import org.joda.time.LocalDateTime
import org.joda.time.DateTime

val now = LocalDate.now
val t = LocalTime.now
val dt = LocalDateTime.now

val uDT = DateTime.now() // calculate from IST


