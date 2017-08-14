package projecteuler

object PrimeSum extends App {

  def checkPrime(num: Long): Boolean = {
    !(2L to Math.sqrt(num).toLong).exists(num % _ == 0)
  }

  val res = 2L + (3L until 2000000L by 2L).filter(checkPrime).sum
  println(res)

}