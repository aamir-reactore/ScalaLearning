import java.time.{LocalDateTime, OffsetDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter

import scala.collection.mutable.ListBuffer
object testdates extends App {
  val d1 = OffsetDateTime.now()
  d1.getOffset
  d1.toEpochSecond

  val dt1 = OffsetDateTime.of(LocalDateTime.of(2017, 5, 12, 2, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val dt2 = OffsetDateTime.of(LocalDateTime.of(2017, 5, 12, 2, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  dt1.isEqual(dt2)
  dt1.atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime

  def overlapping(sd1: OffsetDateTime, ed1: OffsetDateTime, sd2: OffsetDateTime, ed2: OffsetDateTime) = {
    (sd1.isBefore(ed2) || sd1.isEqual(ed2)) && (ed1.isAfter(sd2) || ed1.isEqual(sd2))
  }

  def overlapping1(sd1: OffsetDateTime, ed1: OffsetDateTime, sd2: OffsetDateTime, ed2: OffsetDateTime) = {
    (sd1.isBefore(ed2) || sd1.isEqual(ed2)) && (ed1.isAfter(sd2) || ed1.isEqual(sd2))
  }

  val sd1 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 14, 6, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val ed1 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 14, 9, 30, 20),
    ZoneOffset.ofHoursMinutes(5, 30))

  val sd2 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 14, 10, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val ed2 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 14, 11, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val sd3 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 15, 7, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val ed3 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 15, 9, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val sd4 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 15, 10, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  val ed4 = OffsetDateTime.of(LocalDateTime.of(2017, 4, 15, 11, 30, 22),
    ZoneOffset.ofHoursMinutes(5, 30))

  case class DT(st: OffsetDateTime, et: OffsetDateTime)

  val x1 = DT(sd1, ed1)
  val x2 = DT(sd2, ed2)
  val x3 = DT(sd3, ed3)
  val x4 = DT(sd4, ed4)
  val l: List[DT] = List(x1, x2, x3, x4)


  /*def recursive(ll: List[DT], restList: List[DT], overLapRes: List[Boolean] = List(true)): List[Boolean] = (ll, restList) match {
  case (Nil, _)             => overLapRes
  case (orgList, h :: Nil)  => recursive(orgList.tail, orgList.tail.tail, overLapRes(orgList.head.st, orgList.head.et, h.st, h.et) :: overLapRes)
  case (orgList, h :: tail) => recursive(orgList, tail.tail, overLapRes(orgList.head.st, orgList.head.et, h.st, h.et) :: overLapRes)
}*/

  /*var lb = ListBuffer[Boolean]()
for(i <- l.indices) {
  for(j <- i + 1 until l.size) {
    val res = overlapping(l(i).st,l(i).et,l(j).st,l(j).et)
    lb.append(res)
  }
}
lb*/

  l.combinations(2).map {
    case List(DT(st1, et1), DT(st2, et2)) =>
      overlapping(st1, et1, st2, et2)
  }.toList
}
