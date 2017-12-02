package scala99

//find nth element in a list
object NthListElement extends App {

  val l = List(1, 2, 3, 4, 5, 6)

  //with zero index
  def recursiveNth[T](index: Int, l: List[T]): T = (index, l) match {
    case (0, h :: _) => h
    case (n, _ :: tail) => recursiveNth(n - 1, tail)
    case (_, Nil) => throw new NoSuchElementException("element not found")
  }

  println("nth element of list is = " + recursiveNth(22, l))
}