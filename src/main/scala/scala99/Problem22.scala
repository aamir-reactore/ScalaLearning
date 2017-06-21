package scala99

object ListRange extends App {
  def tailListRange(lower: Int, upper: Int) = {
    def listRange(upper: Int, resList: List[Int]): List[Int] = {
      if (lower > upper) resList else listRange(upper - 1, upper :: resList)
    }

    listRange(upper, Nil)
  }

  println(s"Range between 4 and 9 is ==> ${tailListRange(4,9)}")
  println(s"Range between 4 and 9 is ==> ${List.range(4,9 + 1)}")

}