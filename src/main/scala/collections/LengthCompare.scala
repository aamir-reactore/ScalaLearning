package collections

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