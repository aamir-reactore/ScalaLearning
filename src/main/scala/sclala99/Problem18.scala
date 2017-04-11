package sclala99

object P18 extends App {

  val l = List(1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15,16)

  def usingSlice[T](list:List[T], start:Int, end:Int):List[T] =
    list.slice(start, end)

   def usingDropTake[T](list:List[T], start:Int, end:Int):List[T] =
     list.drop(start).take(end - start)

  println(s"slice using default method = ${usingSlice(l, 4, 3)}")
  println(s"slice using take drop method = ${usingDropTake(l, 4, 3)}")

}