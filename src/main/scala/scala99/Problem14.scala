package scala99

// Duplicate the elements of a list.
object P14 extends App {

  val l = List('a', 'b', 'c')

  val res1 = l.flatMap { x => List.fill(2)(x) }
  println("Duplicate element list = " + res1)

  def duplicateElements[T](l: List[T]) = {
    def duplicate(l: List[T], tempList: List[T]): List[T] = l match {
      case Nil => tempList.reverse
      case (h :: tail) => duplicate(tail, h :: h :: tempList)
    }
    duplicate(l, Nil)
  }

  println("Duplicate element list = " + duplicateElements(l))

  //using foldRight
  def usingFoldRight[T](l: List[T]):List[T] = l.foldRight(List[T]()) { (elem, acc) => elem :: elem :: acc }


  println("Duplicate element list = " + usingFoldRight(l))



}