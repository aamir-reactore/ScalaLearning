package collections.arrays

object CreatingArrays1 extends App {

  val arr1 =  Array(1,2,3,4)
  val arr2 =  Array[Int](1,2,3,4)
  val arr3 =  new Array[Int](6)
  val arr4 =  Array.fill(4)("Li")
  val arr5 =  Array.range(1,10)
  val arr6 =  Array.range(1, 10, 2)
  val arr7 =  Array.range(1, 10, 3)

  arr1 foreach(x => print(x + " "))
  println()
  arr2 foreach(x => print(x + " "))
  println()
  arr3 foreach(x => print(x + " "))
  println()
  arr4 foreach(x => print(x + " "))
  println()
  arr5 foreach(x => print(x + " "))
  println()
  arr6 foreach(x => print(x + " "))
  println()
  arr7 foreach(x => print(x + " "))
  println()
}