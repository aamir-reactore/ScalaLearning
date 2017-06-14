package projecteuler

object Problem1Sol extends App {
  val result = (1 until 1000).view.filter(x => x % 3 == 0 || x % 5 == 0).sum
  println(s"sum of numbers in range => $result")
}