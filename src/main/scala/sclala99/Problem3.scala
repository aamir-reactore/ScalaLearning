package sclala99

//find nth element in a list
object P3 extends App {

  val l = List(1, 2, 3, 4, 5, 6)

  //with zero index
  def recursiveNth[T](index: Int, l: List[T]): T = (index, l) match {
    case (0, h :: _) => h
    case (n, _ :: tail) => recursiveNth(n - 1, tail)
    case (_, Nil) => throw new NoSuchElementException("list is empty")
  }

  //with one index
  def recursiveN[T](l: List[T], index: Int): T = {
    require(index > 0, "index must be greater than 1")
    l match {
      case Nil => throw new NoSuchElementException
      case h :: tail => {
        if (index == 1) h else recursiveN(tail, index - 1)
      }
    }
  }

  println("nth element of list is = " + recursiveNth(4, l))
  println("nth element of list is = " + recursiveN(l, 0))
}