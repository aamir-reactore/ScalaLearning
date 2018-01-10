package scala99

object ListRange extends App {

  def tailListRange(lower: Int, upper: Int): List[Int] = {
    def listRange(upper: Int, resList: List[Int]): List[Int] = {
      if (lower > upper) resList else listRange(upper - 1, upper :: resList)
    }
    listRange(upper, Nil)
  }

  println(s"Range between 4 and 9 is ==> ${tailListRange(4,9)}")
  println(s"Range between 4 and 9 is ==> ${List.range(4,9 + 1)}")



  // The classic functional approach would be to use `unfoldr`, which Scala
  // doesn't have.  So we'll write one and then use it.
  def unfoldRight[A, B](s: B)(f: B => Option[(A, B)]): List[A] =
  f(s) match {
    case None         => Nil
    case Some((r, n)) => r :: unfoldRight(n)(f)
  }
  def rangeFunctional(start: Int, end: Int): List[Int] =
    unfoldRight(start) { n =>
      if (n > end) None
      else Some((n, n + 1))
    }

  println(rangeFunctional(4,9))


}