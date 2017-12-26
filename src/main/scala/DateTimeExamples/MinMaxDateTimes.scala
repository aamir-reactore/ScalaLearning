package DateTimeExamples

import org.joda.time.DateTime

object MinMaxDateTimes1 extends App {

   val dt1 = DateTime.parse("2016-12-01T08:00:00.000Z")
  val dt2 = DateTime.parse("2016-03-02T09:00:00.000Z")
  val dt3 = DateTime.parse("2016-10-10T02:00:00.000Z")
  val dt4 = DateTime.parse("2016-05-22T01:00:00.000Z")
  val dt5 = DateTime.parse("2016-09-14T02:00:00.000Z")
  val dt6 = DateTime.parse("2016-08-17T06:00:00.000Z")

  //val dtList = List(dt1,dt2,dt3,dt4,dt5,dt6)
  val dtList = List(dt1,DateTime.parse("2016-12-01T08:00:00.000Z"))
  val res = dtList.sorted(DateTimeOrdering)
  println(res)
}
object DateTimeOrdering extends Ordering[DateTime] {
  implicit def compare(x: DateTime, y: DateTime) = x.compareTo(y)
}