package projecteuler

import scala.collection.SeqView
import scala.collection.immutable.IndexedSeq

/**
  * For views, check it out
  * https://stackoverflow.com/questions/6799648/in-scala-what-does-view-do
  * http://www.scala-lang.org/docu/files/collections-api/collections_42.html
  */
object PythNumberLimit extends App {
  val sum = 1000
  val resTrip: SeqView[(Int, Int, Int), Seq[_]] = for (
    x <- (1 to sum / 3).view;
    y <- 1 to sum / 2;
    z = sum - x - y
    if isPythagorous(x, y, z)
  ) yield (x, y, z)

  def isPythagorous(a: Int, b: Int, c: Int): Boolean = {
    (a < b && b < c) && (a * a + b * b == c * c) && (a + b + c == 1000)
  }
  val res: (Int, Int, Int) = resTrip.head
  println(s"shy triplets are ==> ${res._1}, ${res._2}, ${res._3}")
  println(s"triplet product is ${res._1 * res._2 * res._3}")
}