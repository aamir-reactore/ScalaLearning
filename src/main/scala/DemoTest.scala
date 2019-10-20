
object DemoTest extends App {

    def pairsCounting(arr: Array[Int]): Int = {
      var result = 0
      var i = 0
      val n = arr.length
      while (i < n) {
        var j = i + 1
        while (j < n) {
          if (arr(i) == arr(j)) {
            result += 1
          }
          j += 1
          j - 1
        }
        i += 1
        i - 1
      }
      result
    }

      val arr = Array(3, 5, 6, 3,3,5)
      System.out.println(pairsCounting(arr))

  val s= (1,2)
  println( s.swap )
}