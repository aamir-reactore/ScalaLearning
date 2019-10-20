package collections.lists

object ReduceLeft1 extends App {

  val l = List(1, 2, 3, 4, 5)

  val res1 = l.reduce(_ + _)
  val res2 = l.reduce((total: Int, curr:Int) => total + curr)
  val res3 = l.sum

  def op(x: Int, y: Int) = x + y

  val res4 = l.reduce(op)

  println(res1)
  println(res2)
  println(res3)
  println(res4)

  //when list is empty
  val emptyList = List.empty
  val res = emptyList.reduce(op) //java.lang.UnsupportedOperationException: empty.reduceLeft, foldLeft to rescue
  println(res)
}

object FoldLeft1 extends App {

  val l = List(1, 2, 3, 4, 5)
  def op(x: Int, y: Int) = x + y

  val res1 = l.foldLeft(0)(op)
  val res2 = l.foldLeft(0)(_ + _)

  println(res1)
  println(res2)

  /**
   * Looks a lit unusual, imagine this
    * List(1, 2, 3).foldLeft(0)(_ + _)
    * // ((0 + 1) + 2) + 3
    * // = 6
    * List(1, 2, 3).foldRight(0)(_ + _)
    * // 1 + (2 + (3 + 0))
    * // = 6
   */
  val listStr = List(" country ", " my ", " is ", " kashmir ")
  val resX = listStr.foldLeft("Hello ")(_ + _)
  val resY = listStr.foldRight("Hello ")(_ + _) //visualize using above diagram

  val resZ = listStr.reverse.foldLeft("Hello ")(_ + _)

  println(resX)
  println(resY)
  println(resZ)

  val emptyList = List.empty
  val res11 = emptyList.foldLeft(0)(op) //not problem if list is empty
  val res22 = emptyList.foldLeft(4)(op) //not problem if list is empty
  println(res11)
  println(res22)

}