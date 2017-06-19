package projecteuler

object FibonacciSeries extends App {

  def fibonacci(num1:Long,num2:Long):Stream[Long] = {
    num1 #:: fibonacci(num2, num1 + num2)
  }
  val result = fibonacci(0,1).view.takeWhile(_ < 4000000).filter(_ % 2 == 0).sum
  println(s"sum of even termed fibonacci =>$result")
}