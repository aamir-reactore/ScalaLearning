package DateTimeExamples


import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object jj extends App {
  val f =  "dd/MM/yyyy HH:mm:ss aa"
  val fm = DateTimeFormat.forPattern(f)
  val s = "10/10/2017 4:16:17 PM"
  val x = DateTime.parse(s,fm)
  println(x)
}