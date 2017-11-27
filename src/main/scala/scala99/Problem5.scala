package scala99

//reversing elements of a list
object ReverseList extends App {

  val l = List(1,2,3,4,5,6)
  def recursiveReverse[T](l:List[T]):List[T] = {
    def reverse(list:List[T], tempList:List[T]):List[T] = list match {
      case Nil       => tempList
      case h :: tail => reverse(tail, h :: tempList)
    }
    reverse(l,Nil)
  }
  def foldReverse[T](l:List[T]) = l.foldLeft(Li()((acc, b) => b :: acc)

  def martinOderskyLectureReverse(l: List[Int]):List[Int] = l match {
    case List()    => l
    case h :: tail => martinOderskyLectureReverse(tail) ++ List(h) // ++ ==> :::
  }

  println("reversed elements of a list = " + recursiveReverse(l))
  println("reversed elements of a list = " + foldReverse(l))
  println("reversed elements of a list = " + l.reverse)
  println("martinOderskyLectureReverse  = " + martinOderskyLectureReverse(l))

}