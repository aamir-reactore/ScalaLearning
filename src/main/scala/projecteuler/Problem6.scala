package projecteuler

object SumSquareDiff extends App {

  val sum = (1 to 100).sum
  val sumSquare = sum * sum
  val squareSum = (1 to 100).map(x => x * x).sum

  println(s"difference ${sumSquare - squareSum}")
}