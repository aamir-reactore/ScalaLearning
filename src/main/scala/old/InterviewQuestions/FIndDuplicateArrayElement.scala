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
