package eciffocodeexamples


import org.joda.time.LocalDate
/**
  * General Algorithm (applies to each employee in the list of employees assigned for b.a shift)
  * 1. Skip public holidays
  * 2. Skip weekdays with false record
  * 3. split records with working days and weekdays
  * 4. If continuous shift then assign all resulting split true records with same shift
  * 5. If rotational shift then first calculate shift sequence and
  *    then assign to respective shifts based on shift duration chane.
  * 6. concatenate results of step 4 or 5 with split 3 weekdays records
  * 7. sort records based on local date time
  * 8. build entity for table insertion
  * */
object ShiftTest extends App {

  def shiftAssignmentWithRotation(currentShift:Long,rosterShiftsSequenceMappings: List[(Long, Long)],
                       empRosterDates: List[(LocalDate, Boolean)], shiftChangeDuration: Long): List[((LocalDate, Boolean), Option[Long])] = {
    val nexShiftSequence = rotationalShiftsShuffle(currentShift,rosterShiftsSequenceMappings)
    assignRotationalShifts(empRosterDates,shiftChangeDuration,nexShiftSequence)
  }
  // if shiftduration is 1 then assign all employees with current shift id
  def assignRotationalForShiftDuration1(empRosterDates: List[(LocalDate, Boolean)], shiftId: Long) = {
    def assignRecursive(shiftId: Long, list: List[(LocalDate, Boolean)], resultList: List[((LocalDate, Boolean), Option[Long])]): List[((LocalDate, Boolean), Option[Long])] = list match {
      case Nil => resultList.reverse
      case h :: tail => assignRecursive(shiftId, tail, (h, Some(shiftId)) :: resultList)
    }
    assignRecursive(shiftId, empRosterDates, Nil)
  }

  def assignRotationalShifts(empRosterDates: List[(LocalDate, Boolean)], shiftChangeDuration: Long, nextShiftSequence: List[Long]) = {
    def assignRecursive(n: Long, list: List[(LocalDate, Boolean)], resultList: List[((LocalDate, Boolean), Option[Long])], shiftList: List[Long]): List[((LocalDate, Boolean), Option[Long])] = (n, list, shiftList) match {
      case (_, Nil, _) => resultList.reverse
      case (_, _, Nil) => resultList.reverse
      case (c, h :: tail, List(x)) => assignRecursive(c - 1, tail, (h, Some(x)) :: resultList, List(x))
      case (1, h :: tail, shifts) =>
        assignRecursive(shiftChangeDuration - 1, tail, (h, Some(shifts.tail.head)) :: resultList, shifts.tail ++ List(shifts.head))
      case (c, h :: tail, shifts) =>
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

  //combining rotationalShiftsShuffle and assignRotationalShifts

}