package officecodeexamples

import org.joda.time.LocalDate

object ShiftTest extends App {
  def assignRotationalShifts(empRosterDates: List[(LocalDate, Boolean)], shiftChangeDuration: Long, nextShiftSequence: List[Long]) = {
    def assignRecursive(n: Long, list: List[(LocalDate, Boolean)], resultList: List[((LocalDate, Boolean), Option[Long])], shiftList: List[Long]): List[((LocalDate, Boolean), Option[Long])] = (n, list, shiftList) match {
      case (_, Nil, _) => resultList.reverse
      case (_, _, Nil) => resultList.reverse
      case (c, h :: tail, List(x)) => assignRecursive(c - 1, tail, (h, Some(x)) :: resultList, List(x))
      case (1, h :: tail, shifts) =>
        println("for record >>>>>>>> " + h._1 + "shift id is " + shifts.tail.head)
        assignRecursive(shiftChangeDuration - 1, tail, (h, Some(shifts.tail.head)) :: resultList, shifts.tail ++ List(shifts.head))
      case (c, h :: tail, shifts) =>
        println("for record " + h._1 + "shift id is " + shifts.tail.head)
        assignRecursive(c - 1, tail, (h, Some(shifts.head)) :: resultList, shifts)
    }

    require(nextShiftSequence.nonEmpty)
    assignRecursive(shiftChangeDuration, empRosterDates, Nil, nextShiftSequence)
  }

  def rotationalShiftsShuffle(currentShift: Long, rosterShiftsSequenceMappings: List[(Long, Long)]): List[Long] = rosterShiftsSequenceMappings match {
    case Nil => Nil
    case _ =>
      val sortedList = rosterShiftsSequenceMappings.sortBy(x => x._2)
      val i = sortedList.indexWhere(_._1 == currentShift)
      if (i == -1) sortedList.map(_._1) else (sortedList(i) :: sortedList.take(i) ++ sortedList.drop(i + 1)).map(_._1)

    //if(i == -1) Nil else (sortedList.drop(i + 1) ++ sortedList.take(i + 1)).map(_._1)
  }
}