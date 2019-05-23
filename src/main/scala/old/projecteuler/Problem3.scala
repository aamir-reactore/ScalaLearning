package projecteuler

object PrimeFactors extends App {
  def primeFactors(num:Long):List[Long] = {
    val res = (2L to Math.sqrt(num).toLong).find(num % _ == 0)
    res match {
      case Some(s) => s :: primeFactors(num / s)
      case None => List(num)
    }
  }
  val result = primeFactors(600851475143L).max
  println(s"max prime factor => $result")
}