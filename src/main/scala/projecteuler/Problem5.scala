package projecteuler

object BetweenLimitDivisible extends App {

  /**
    * takes lot of time to finish.
    */
  def checkDiv(num: Long): Boolean = {
    (20 to 11 by -1).forall(num % _ == 0)
  }

  //val res1 = Iterator.from(1).find(checkDiv(_))

   val res = (1L to 20L).reduceLeft{(a,b) =>lcm(a,b)}
  println(res)


  def gcd(num1: Long, num2: Long): Long =
        if (num1 % num2 == 0) num2 else gcd(num2, num1 % num2)

  def lcm(num1:Long, num2:Long):Long =
        (num1 * num2) / gcd(num1, num2)

}