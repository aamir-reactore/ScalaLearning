package implicits

 import java.time.LocalDateTime

object LocalDateExtension {

  implicit class extension(s: LocalDateTime) {
    def between(start: LocalDateTime, end: LocalDateTime): Boolean = {
      s.isAfter(start) && s.isBefore(end)
    }
  }

}

object ll extends App {

  import java.time.format.DateTimeFormatter

  import LocalDateExtension._

  val str1 = "1986-04-08 12:30"
  val str2 = "1988-03-08 12:30"
  val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
  val formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
  val dateTime1 = LocalDateTime.parse(str1, formatter1)
  val dateTime2 = LocalDateTime.parse(str2, formatter2)

  val haveToCheck1 = "1987-03-08 12:30"
  val formatterCheck1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
  val dtCheck = LocalDateTime.parse(haveToCheck1, formatterCheck1)

  println(dtCheck.between(dateTime1, dateTime2))


}