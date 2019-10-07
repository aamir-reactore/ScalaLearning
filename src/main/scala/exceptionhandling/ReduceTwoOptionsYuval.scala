package exceptionhandling

/*
Followed this tutorial
https://blog.yuvalitzchakov.com/reducing-two-options-in-scala/
 */
object ReduceTwoOptionsYuvalTest extends App {

  def reduce[T](a: Option[T], b: Option[T], f: (T, T) => T): Option[T] = (a, b) match {
    case (Some(first), Some(second)) => Some(f(first, second))
    case (Some(_), None)             => a
    case (None, Some(_))             => b
    case _                           => None
  }
}

object ViewOptionCollection1 extends App {
  val opt: Some[Int] = Some(3)
  println(opt.map(_ + 3))
  println(opt.filter(_ < 2))
  println(opt.filter(_ > 2))

  println("---------------------------------")

  val empty: Option[Int] = None
  println(empty.map(_ + 8))
  println(empty.filter(_ < 8))


}

object ViewOptionCollection2 extends App {

  val l1 = List(1)
  val l2 = List(2)

  val l3 = l1 ++ l2

  val l4 = List('a')

  val l5 = l3 ++ l4

  println(l5)
}

//apply above to options as we are viewing them as collections

object ViewOptionCollection3 extends App {

  val first = Some(3)
  val second = Some(42)

  val r1: Iterable[Int] = first ++ second

  println("r1 ==> " + r1.toList)
  val r2 = first ++ None
  println("r2 ==> " + r2.toList)
  val r3 = None ++ second
  println("r3 ==> " + r3.toList)

}

object ViewOptionCollection4 extends App {

  def reduce[T](a: Option[T], b: Option[T], f: (T, T) => T):Option[T] = {
    (a ++ b).reduceLeftOption(f)
  }
}

object ViewOptionCollection5 extends App {

  val first = Some(3)
  val second = Some(42)
  val third = Some(46)
  val fourth = None

  val result: Option[Int] = (first ++ second ++ third ++ fourth).reduceLeftOption(math.max)
  println(result)
}


