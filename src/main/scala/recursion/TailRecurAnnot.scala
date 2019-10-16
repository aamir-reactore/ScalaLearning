package recursion

import scala.annotation.tailrec

object TailRecurAnnotationCompileError extends App {

  import scala.annotation.tailrec

 /* @tailrec
  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case x :: xs => x + sum(xs)
  }*/ // recursive call not in tail position.
}

object TailRecurAnnot extends App {

  @tailrec
  private def sumWithAccumulator(list: List[Int], accumulator: Int): Int = {
    list match {
      case Nil => accumulator
      case x :: xs => sumWithAccumulator(xs, accumulator + x)
    }
  }
  val res = sumWithAccumulator(List(1,2,3,4,5), 0)
  println(res)

}