package projecteuler

import projecteuler.PrimeFactors._

object TriangularNumberMaxFactors extends App {
  def factors(num: Long): Int = {
    primeFactors(num).groupBy(identity).map(_._2.size + 1).product
  }

  def triangularNumber(num: Long) = {
    def triangular(num: Long, acc: Long): Long = {
      if (num == 0) acc else triangular(num - 1, num + acc)
    }
    triangular(num, 0L)
  }

  val r = Iterator.from(1).map(triangularNumber(_)).find(factors(_) > 500)
  println(r.get)
}