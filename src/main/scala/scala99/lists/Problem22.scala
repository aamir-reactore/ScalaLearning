package scala99.lists

object ListRange extends App {

  def tailListRange(lower: Int, upper: Int): List[Int] = {
    def listRange(upper: Int, resList: List[Int]): List[Int] = {
      if (lower > upper) resList else listRange(upper - 1, upper :: resList)
    }
    listRange(upper, Nil)
  }
  //using scanLeft
  def usingScanLeft(start:Int,end:Int) = List.fill(end-start)(1).scanLeft(4)(_ +_)

  println(s"Range between 4 and 9 is ==> ${tailListRange(4,9)}")
  println(s"Range between 4 and 9 is ==> ${List.range(4,9 + 1)}")
  println(s"Range between 4 and 9 is ==> ${usingScanLeft(4,9)}")



  // The classic functional approach would be to use `unfoldr`, which Scala
  // doesn't have.  So we'll write one and then use it.
  def unfoldRight[A, B](s: B)(f: B => Option[(A, B)]): List[A] =
  f(s) match {
    case None         => Nil
    case Some((r, n)) => r :: unfoldRight(n)(f)
  }
  def rangeFunctional(start: Int, end: Int): List[Int] =
    unfoldRight(start) { n =>
      if (n > end) None else Some((n, n + 1))
    }

  println(s"using range functional:==> ${rangeFunctional(4,9)}")



  //for testing try below options.
  val x: (Int => Option[(Int, Int)]) => List[Int] = unfoldRight[Int,Int](1)

  def tst(x:Int)(f:Int => Long): Long = x + f(x)

  def r(y:Int) = y + 4
  val  rrr = tst(1)(r)

  println(s"rr test $rrr")

}