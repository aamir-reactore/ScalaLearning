package martinoderskyvideos

/**
  * Basic formula to evaluate:
  * x(n+1) = x(n) - f(x(n)) / f'(x(n))
  */
object NewtonsFormula extends App {



  def squareRoot(num: Double): Double = {
    def abs(num: Double) = if (num < 0) -num else num

    def isGoodEnough(guess: Double): Boolean = abs(guess * guess - num) / num < 0.001

    def improve(guess: Double): Double = (guess + num / guess) / 2

    def squareIterator(guess: Double): Double = {
      if (isGoodEnough(guess)) guess else squareIterator(improve(guess))
    }
    squareIterator(1)
  }

  println(s"square root is ${squareRoot(2)}")

}