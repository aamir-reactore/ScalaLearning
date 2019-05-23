package scala99.lists

//finding length of b.a list
object ListLength extends App {

  val l = List(1,2,3,4,5,6)
  def foldLength[T](l: List[T]): Int = l.foldLeft(0)((a, _) => a + 1)

  def recursiveLength[T](l: List[T]): Int = {
    def findLength(acc: Int, l: List[T]): Int = l match {
      case _ :: tail => findLength(acc + 1, tail)
      case Nil => acc
    }
    findLength(0, l)
  }

  println("length of list is = " + foldLength(l))
  println("length of list is = " + recursiveLength(l))
  println("length of list is = " + l.length)
}
