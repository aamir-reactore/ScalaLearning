package officecodeexamples

import org.joda.time.LocalDate
import org.scalatest.FunSuite

class AssignShiftTest extends FunSuite {


  test("assignRotationalShifts returns single record when empRosterDates list when only 1 record is passed") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-05"), true))
    val shiftChangeDuration = 6L
    val nextShiftSequence = List(2l, 1L, 3L)
    val resultList = List(((LocalDate.parse("2016-12-05"), true), Some(2L)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)
    assert(res == resultList)
  }

  test("assignRotationalShifts returns multiple records when  empRosterDates list contains multiple records") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftChangeDuration = 6l
    val nextShiftSequence = List(1l, 2L, 3L)
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(1l)), ((LocalDate.parse("2016-12-03"), true), Some(1l)),
      ((LocalDate.parse("2016-12-04"), true), Some(1l)),
      ((LocalDate.parse("2016-12-06"), true), Some(1l)), ((LocalDate.parse("2016-12-07"), true), Some(1l)),
      ((LocalDate.parse("2016-12-09"), true), Some(2l)), ((LocalDate.parse("2016-12-10"), true), Some(2l)),
      ((LocalDate.parse("2016-12-11"), true), Some(2l)), ((LocalDate.parse("2016-12-12"), true), Some(2l)), ((LocalDate.parse("2016-12-14"), true), Some(2l)), ((LocalDate.parse("2016-12-16"), true), Some(3l))
      , ((LocalDate.parse("2016-12-17"), true), Some(3l)), ((LocalDate.parse("2016-12-18"), true), Some(3l)), ((LocalDate.parse("2016-12-20"), true), Some(3l)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)
    assert(res == resultList)
  }


  test("assignRotationalShifts returns multiple records when  empRosterDates list contains multiple records but only 2 shiftId exists in the nextShiftSequence") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftChangeDuration = 6l
    val nextShiftSequence = List(1L, 2l)
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(1l)), ((LocalDate.parse("2016-12-03"), true), Some(1l)),
      ((LocalDate.parse("2016-12-04"), true), Some(1l)),
      ((LocalDate.parse("2016-12-06"), true), Some(1l)), ((LocalDate.parse("2016-12-07"), true), Some(1l)),
      ((LocalDate.parse("2016-12-09"), true), Some(2l)), ((LocalDate.parse("2016-12-10"), true), Some(2l)),
      ((LocalDate.parse("2016-12-11"), true), Some(2l)), ((LocalDate.parse("2016-12-12"), true), Some(2l)), ((LocalDate.parse("2016-12-14"), true), Some(2l)), ((LocalDate.parse("2016-12-16"), true), Some(1l))
      , ((LocalDate.parse("2016-12-17"), true), Some(1l)), ((LocalDate.parse("2016-12-18"), true), Some(1l)), ((LocalDate.parse("2016-12-20"), true), Some(1l)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)
    assert(res == resultList)
  }


  test("assignRotationalShifts returns multiple records when  empRosterDates list contains multiple records but only 1 shiftId exists i.e  nextShiftSequence is empty") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftChangeDuration = 6l
    val nextShiftSequence = List(1l)
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(1l)), ((LocalDate.parse("2016-12-03"), true), Some(1l)), ((LocalDate.parse("2016-12-04"), true), Some(1l)),
      ((LocalDate.parse("2016-12-06"), true), Some(1l)), ((LocalDate.parse("2016-12-07"), true), Some(1l)), ((LocalDate.parse("2016-12-09"), true), Some(1l)), ((LocalDate.parse("2016-12-10"), true), Some(1l)),
      ((LocalDate.parse("2016-12-11"), true), Some(1l)), ((LocalDate.parse("2016-12-12"), true), Some(1l)), ((LocalDate.parse("2016-12-14"), true), Some(1l)), ((LocalDate.parse("2016-12-16"), true), Some(1l))
      , ((LocalDate.parse("2016-12-17"), true), Some(1l)), ((LocalDate.parse("2016-12-18"), true), Some(1l)), ((LocalDate.parse("2016-12-20"), true), Some(1l)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)

    assert(res == resultList)
  }


  test("assignRotationalShifts returns multiple records when  empRosterDates list contains multiple records but shiftChangeDuration is small i.e 4 and  nextShiftSequence has 3 records") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftChangeDuration = 4l
    val nextShiftSequence = List[Long](1, 2, 3)
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(1l)), ((LocalDate.parse("2016-12-03"), true), Some(1l)), ((LocalDate.parse("2016-12-04"), true), Some(1l)),
      ((LocalDate.parse("2016-12-06"), true), Some(2l)), ((LocalDate.parse("2016-12-07"), true), Some(2l)), ((LocalDate.parse("2016-12-09"), true), Some(2l)), ((LocalDate.parse("2016-12-10"), true), Some(3l)),
      ((LocalDate.parse("2016-12-11"), true), Some(3l)), ((LocalDate.parse("2016-12-12"), true), Some(3l)), ((LocalDate.parse("2016-12-14"), true), Some(1l)), ((LocalDate.parse("2016-12-16"), true), Some(1l))
      , ((LocalDate.parse("2016-12-17"), true), Some(1l)), ((LocalDate.parse("2016-12-18"), true), Some(2l)), ((LocalDate.parse("2016-12-20"), true), Some(2l)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)

    assert(res == resultList)
  }


  test("assignRotationalShifts returns multiple records but shiftChangeDuration is more than empRosterDateList records, so all dates with same shift id") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftChangeDuration = 30l
    val nextShiftSequence = List[Long](1, 2, 3)
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(1l)), ((LocalDate.parse("2016-12-03"), true), Some(1l)), ((LocalDate.parse("2016-12-04"), true), Some(1l)),
      ((LocalDate.parse("2016-12-06"), true), Some(1l)), ((LocalDate.parse("2016-12-07"), true), Some(1l)), ((LocalDate.parse("2016-12-09"), true), Some(1l)), ((LocalDate.parse("2016-12-10"), true), Some(1l)),
      ((LocalDate.parse("2016-12-11"), true), Some(1l)), ((LocalDate.parse("2016-12-12"), true), Some(1l)), ((LocalDate.parse("2016-12-14"), true), Some(1l)), ((LocalDate.parse("2016-12-16"), true), Some(1l))
      , ((LocalDate.parse("2016-12-17"), true), Some(1l)), ((LocalDate.parse("2016-12-18"), true), Some(1l)), ((LocalDate.parse("2016-12-20"), true), Some(1l)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)

    assert(res == resultList)
  }

  test("assignRotationalShifts returns multiple records but shiftChangeDuration is 2 and nextShiftSequence is 1,2") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftChangeDuration = 2l
    val nextShiftSequence = List[Long](1, 2)
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(1l)), ((LocalDate.parse("2016-12-03"), true), Some(2l)),
      ((LocalDate.parse("2016-12-04"), true), Some(1l)),
      ((LocalDate.parse("2016-12-06"), true), Some(2l)), ((LocalDate.parse("2016-12-07"), true), Some(1l)),
      ((LocalDate.parse("2016-12-09"), true), Some(2l)), ((LocalDate.parse("2016-12-10"), true), Some(1l)),
      ((LocalDate.parse("2016-12-11"), true), Some(2l)), ((LocalDate.parse("2016-12-12"), true), Some(1l)),
      ((LocalDate.parse("2016-12-14"), true), Some(2l)), ((LocalDate.parse("2016-12-16"), true), Some(1l))
      , ((LocalDate.parse("2016-12-17"), true), Some(2l)), ((LocalDate.parse("2016-12-18"), true), Some(1l)),
      ((LocalDate.parse("2016-12-20"), true), Some(2l)))
    val res = ShiftTest.assignRotationalShifts(empRosterDatesList, shiftChangeDuration, nextShiftSequence)

    assert(res == resultList)
  }
/*
   check with manju if shiftChangeDuration is 1 is this the test case to be passed.
 */
  test("assignRotationalForShiftDuration1 return records with same shiftId when shiftChangeDuration is 1") {
    val empRosterDatesList = List((LocalDate.parse("2016-12-02"), true), (LocalDate.parse("2016-12-03"), true), (LocalDate.parse("2016-12-04"), true),
      (LocalDate.parse("2016-12-06"), true), (LocalDate.parse("2016-12-07"), true), (LocalDate.parse("2016-12-09"), true), (LocalDate.parse("2016-12-10"), true),
      (LocalDate.parse("2016-12-11"), true), (LocalDate.parse("2016-12-12"), true), (LocalDate.parse("2016-12-14"), true), (LocalDate.parse("2016-12-16"), true)
      , (LocalDate.parse("2016-12-17"), true), (LocalDate.parse("2016-12-18"), true), (LocalDate.parse("2016-12-20"), true))
    val shiftId = 8
    // after shuffling recursion this will be the structure
    val resultList = List(((LocalDate.parse("2016-12-02"), true), Some(8l)), ((LocalDate.parse("2016-12-03"), true), Some(8l)),
      ((LocalDate.parse("2016-12-04"), true), Some(8l)),
      ((LocalDate.parse("2016-12-06"), true), Some(8l)), ((LocalDate.parse("2016-12-07"), true), Some(8l)),
      ((LocalDate.parse("2016-12-09"), true), Some(8l)), ((LocalDate.parse("2016-12-10"), true), Some(8l)),
      ((LocalDate.parse("2016-12-11"), true), Some(8l)), ((LocalDate.parse("2016-12-12"), true), Some(8l)),
      ((LocalDate.parse("2016-12-14"), true), Some(8l)), ((LocalDate.parse("2016-12-16"), true), Some(8l))
      , ((LocalDate.parse("2016-12-17"), true), Some(8l)), ((LocalDate.parse("2016-12-18"), true), Some(8l)),
      ((LocalDate.parse("2016-12-20"), true), Some(8l)))
    val res = ShiftTest.assignRotationalForShiftDuration1(empRosterDatesList, shiftId)

    assert(res == resultList)
  }
}