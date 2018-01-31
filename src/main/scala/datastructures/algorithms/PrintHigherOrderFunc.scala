package datastructures.algorithms

object IntOps {
  implicit class ExtendedInt(n: Int) {
    def times(fn: Int => Unit): Unit =
      (0 until n).foreach(fn)
  }
}

object IntOpsTest extends App {
  import IntOps._
  5.times{ _ => println("hi") }
}