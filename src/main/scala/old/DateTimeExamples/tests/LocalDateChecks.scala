
package DateTimeExamples

  import org.joda.time.LocalDate
/**
  * Find if LocalDate falls between two LocalDate Ranges
  */
object LocalDateCheckTest1 extends App {
  def isALocalDateBetweenTwoLocalDate(date:LocalDate,startDate:LocalDate,endDate:LocalDate) = {
    !date.isBefore(startDate) && !date.isAfter(endDate)
  }
  def areLocalDatesBetweenTwoLocalDates(date1: LocalDate,date2: LocalDate, startDate: LocalDate, endDate: LocalDate) = {
    !date1.isBefore(startDate) && !date2.isAfter(endDate)
  }
}

