package caseclasses

object ConstructDeconstructTest1 extends App {

  case class FullName(firstName: String, lastName: String)

  val me: FullName = FullName("Aamir", "Fayaz") //constructing
  val FullName(firstName, secondName) = me //deconstructing
}

/*
 * Followed this post
 * https://medium.com/wix-engineering/scala-pattern-matching-apply-the-unapply-7237f8c30b41
 */
import java.time.{LocalDate, LocalDateTime, LocalTime}

object DateTime {
  def unapply(dt: LocalDateTime): Some[(LocalDate, LocalTime)] =
    Some((dt.toLocalDate, dt.toLocalTime))
}

object Date {
  def unapply(d: LocalDate): Some[(Int, Int, Int)] =
    Some((d.getYear, d.getMonthValue, d.getDayOfMonth))
}

object Time {
  def unapply(t: LocalTime): Some[(Int, Int, Int)] =
    Some((t.getHour, t.getMinute, t.getSecond))
}

object DateTimeSeq {
  def unapplySeq(dt: LocalDateTime): Some[Seq[Int]] =
    Some(Seq(
      dt.getYear, dt.getMonthValue, dt.getDayOfMonth,
      dt.getHour, dt.getMinute, dt.getSecond))
}

object ConstructDeconstruct2 extends App {
 //everything deconstruction....

  val Date(year1, month1, day1) = LocalDate.now
  val Time(hour1, minute1, second1) = LocalTime.now
  val DateTime(Date(y1, m1, d1), Time(h1, mm1, s1)) = LocalDateTime.now

  val dt@DateTime(date@Date(y2, m2, d2), time@Time(h2, mm2, s2)) = LocalDateTime.now

  val DateTimeSeq(year2, month2, day2, hour2, _*) = LocalDateTime.now  //omit others take only four

}

object AM {
  def unapply(t: LocalTime): Option[(Int, Int, Int)] =
    t match {
      case Time(h, m, s) if h < 12 => Some((h, m, s))
      case _ => None
    }
}

object PM {
  def unapply(t: LocalTime): Option[(Int, Int, Int)] =
    t match {
      case Time(12, m, s) => Some(12, m, s)
      case Time(h, m, s) if h > 12 => Some(h - 12, m, s)
      case _ => None
    }
}

object ConstructDeconstruct3 extends App {

  //if None is returned in first case then it will go to next case statement.
  val result: String = LocalTime.now match {
    case t @ AM(h, m, _) =>
      f"$h:$m AM ($t precisely)"
    case t @ PM(h, m, _) =>
      f"$h%2d:$m%02d PM ($t precisely)"
  }

  println(result)

}