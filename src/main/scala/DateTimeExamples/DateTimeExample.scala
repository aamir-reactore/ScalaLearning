package DateTimeExamples

/**
  * Created by Aamir-Fayaz on Oct, 2017
  **/
class DateTimeExample {
  import org.joda.time.format.DateTimeFormat
  import org.joda.time.{DateTime, DateTimeZone, LocalDate, LocalTime}

  val d = "05.10.2017" //ist
  val t = "16:54:57" //ist
  val format = DateTimeFormat.forPattern("dd.MM.yyyy")

  val lt = LocalTime.parse(t)
  val ld = LocalDate.parse(d, format)

  def convertToUTC(dateCreatedInLong: Long) = {
    val clientTime = new DateTime(dateCreatedInLong).withZoneRetainFields(DateTimeZone.forID("Asia/Kolkata"))
    println("clientTime time >>"+clientTime)

    val utcTime = clientTime.withZone(DateTimeZone.UTC)
    println("utcTime time >>"+utcTime)
    val fmtTP = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val strTP = fmtTP.print(utcTime)
    DateTime.parse(strTP)
  }

  val k = ld.toDateTime(lt) //.withZoneRetainFields(DateTimeZone.forID("Asia/Kolkata"))
  val g = k.withZoneRetainFields(DateTimeZone.forID("Asia/Kolkata"))
  val ss = new DateTime(g.getMillis)
  val m = convertToUTC(g.getMillis)



  val ll = DateTime.now()
  val xxx = new DateTime(ll.getMillis)
}
