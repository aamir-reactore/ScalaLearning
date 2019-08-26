package old.InterviewQuestions

object FIndDuplicateArrayElement extends App {
  /**
    * for array size n, having elements from 1 to n-1, find duplicate elements in array
    */

  val arr = Array(2, 3, 4, 5, 2, 5)
  for (x <- arr.indices) {
    if (arr(Math.abs(arr(x))) > 0)
      arr(Math.abs(arr(x))) = -arr(Math.abs(arr(x)))
    else
      println(Math.abs(arr(x)))
  }


}
object classtest extends App {

  abstract class Element {
    def contents: Array[String]
  }

  class ArrElement1(arr: Array[String]) extends Element {
    def contents: Array[String] = arr
  }


  class ArrElement2(arr: Array[String]) extends Element {
    val contents: Array[String] = arr
  }

  class ArrElement3(val contents: Array[String]) extends Element

}
