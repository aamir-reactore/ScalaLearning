package sclala99

//reversing elements of a list
object P5 extends App {

  val l = List(1,2,3,4,5,6)
  def recursiveReverse[T](l:List[T]):List[T] = {
    def reverse(list:List[T], tempList:List[T]):List[T] = list match {
      case Nil => tempList
      case h :: tail => reverse(tail, h :: tempList)
    }
    reverse(l,Nil)
  }
  def foldReverse[T](l:List[T]) = l.foldLeft(List[T]())((acc, b) => b :: acc)

  println("reversed elements of a list = " + recursiveReverse(l))
  println("reversed elements of a list = " + foldReverse(l))
  println("reversed elements of a list = " + l.reverse)

}