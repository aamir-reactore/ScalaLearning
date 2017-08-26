val x = 1  #:: 2  #:: Stream.empty
x.tail



def primeStream(s: Stream[Int]): Stream[Int] =
  Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
val primes = primeStream(Stream.from(2))



"madam".zip("hello")
def tr(a:Int, b:Int) = if(a > b) a else b
val res = (1 to 10).reduceLeft{(a,b) => tr(a,b)}


val list = List(1, 2, 3, 4)
list.foreach(println(_))
list.foreach(x => println(x + "test"))

val numList1 = List(1,2)
val numList2 = List(3,4)

for {
   first <- numList1
  second <- numList2
} yield first + second

def rotationalShiftsShuffle(currentShift: Long, rosterShiftsSequenceMappings: List[(Long, Long)]): List[Long] = rosterShiftsSequenceMappings match {
  case Nil => Nil
  case _ =>
    val sortedList = rosterShiftsSequenceMappings.sortBy(x => x._2)
    val i = sortedList.indexWhere(_._1 == currentShift)
    if (i == -1) sortedList.map(_._1) else (sortedList(i) :: sortedList.take(i) ++ sortedList.drop(i + 1)).map(_._1)

  //if(i == -1) Nil else (sortedList.drop(i + 1) ++ sortedList.take(i + 1)).map(_._1)
}
val rosterShiftsSequenceMappings = List[(Long,Long)]((2,1),(3,2),(1,3))
rotationalShiftsShuffle(1L,rosterShiftsSequenceMappings)