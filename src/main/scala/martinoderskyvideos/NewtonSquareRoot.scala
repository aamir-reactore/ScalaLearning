package martinoderskyvideos

/**
  * Basic formula to evaluate:
  * x(n+1) = x(n) - f(x(n)) / f'(x(n))
  */
object NewtonsFormula extends App {

  def abs(num: Double) = if (num < 0) -num else num

  def isGoodEnough(num: Double, guess: Double): Boolean = abs(guess * guess - num) / num < 0.001

  def improve(num: Double, guess: Double): Double = (guess + num / guess) / 2

  def squareIterator(num: Double, guess: Double): Double = {
    if (isGoodEnough(num, guess)) guess else squareIterator(num, improve(num, guess))
  }

  def squareRoot(num: Double): Double = squareIterator(num, 1)

  println(s"square root is ${squareRoot(2)}")

}