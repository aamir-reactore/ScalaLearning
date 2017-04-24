package sclala99

// first index is 0 based and second one is 1 based (kinda like substring)
object P18 extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')

  def usingSlice[T](list:List[T], start:Int, end:Int):List[T] =
    list.slice(start, end)

   def usingDropTake[T](list:List[T], start:Int, end:Int):List[T] =
     list.drop(start).take(end - start)

  println(s"slice using default method = ${usingSlice(l, 3, 7)}")
  println(s"slice using take drop method = ${usingDropTake(l, 3, 7)}")

}