package collections

import scala.collection.immutable.Queue

object LengthCompareTest1 extends App {

   val l1 = List(1,2,3,4,5)

   println(l1.length == 1)
   println(l1.lengthCompare(1) == 0)

  println(l1.length > 1)
  println(l1.lengthCompare(1) > 0)

  println(l1.length < 1)
  println(l1.lengthCompare(1) < 0)

  println(l1.length != 1)
  println(l1.lengthCompare(1) != 0)
}
object jjj extends App {
  val l: List[Int] = List(1,2,3,4)
  println(l)
}