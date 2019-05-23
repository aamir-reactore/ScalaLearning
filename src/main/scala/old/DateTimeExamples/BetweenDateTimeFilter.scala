import org.joda.time.{DateTime, LocalDate}

object test1 extends App {
  def isDateTimeBetween(date: DateTime, startDate: DateTime, endDate: DateTime): Boolean = {
    !date.isBefore(startDate) && !date.isAfter(endDate)
  }

  def isDateTimeOverlapping(sd1: DateTime, ed1: DateTime, sd2: DateTime, ed2: DateTime): Boolean = {
    //including boundary
    //(sd1.isBefore(ed2) || sd1.isEqual(ed2)) && (ed1.isAfter(sd2) || ed1.isEqual(sd2))
    sd1.isBefore(ed2) && ed1.isAfter(sd2)
  }
  def isLocalDatesOverlapping(sd1: LocalDate, ed1: LocalDate, sd2: LocalDate, ed2: LocalDate): Boolean = {
    //including boundary
    (sd1.isBefore(ed2) || sd1.isEqual(ed2)) && (ed1.isAfter(sd2) || ed1.isEqual(sd2))
    //excluding Boundary
    //    sd1.isBefore(ed2)  && ed1.isAfter(sd2)
  }
}