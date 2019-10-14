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

object OptionMap1 extends App {

  val x1 = Some(2)
  val x2 = None
  val x3 = Some(4)
  val x4 = None

  val l = List(x1, x2, x3, x4)
  def sqrt(i: Int) = i * i
  val res1: List[Option[Int]] = l.map(_.map(sqrt))
  val res2: List[Int] = l.flatMap(_.map(sqrt))
}

object OptionMap2 extends App {

  case class User(name: String, age:Option[Int])
  def prettyPrint(user: User) = List(Option(user.name), user.age).flatten.mkString(", ")

  val user1 = User("Aamir", Some(30))
  val user2 = User("Aqsa", None)

  println( prettyPrint(user1))
  println( prettyPrint(user2))
}
