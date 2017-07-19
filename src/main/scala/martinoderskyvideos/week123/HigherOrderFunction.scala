package martinoderskyvideos.week123

object HigherOrderFunctionExample1 extends App {

  def sum(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }

  println(s"sum from 1 to 5 is ${sum(x => x, 1, 5)}")
  println(s"cube from 1 to 5 is ${sum(x => x * x * x, 1, 5)}")

}

object HigherOrderFunctionExample2 extends App {
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int = {
      if (a > b) 0 else f(a) + sumF(a + 1, b)
    }
    sumF
  }

  val res: Int = sum(x => x * x * x)(1, 5)
  println(res)
}

object HigherOrderFunctionExample3 extends App {

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    // here type of sum is (Int => Int) => (Int, Int) => Int
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }
  val res: Int = sum(x => x * x * x)(1, 5)
  println(res)

}

//function types associate to the right i.e Int => Int => Int equals Int => (Int => Int )

object HigherOrderFunctionExample4 extends App {

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    // here type of sum is (Int => Int) => (Int, Int) => Int
    if (a > b) 1 else f(a) * product(f)(a + 1, b)
  }

  val res: Int = product(x => x)(1, 5)
  println(res)

  def factorial(n: Int) = product(x => x)(1, n)

  println(s"factorial of 6 is ${factorial(6)}")
}

/**
  * Combine sum and product in one function.
  */
object HigherOrderFunctionExample5 extends App {

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, base: Int)(a: Int, b: Int): Int = {
    if (a > b) base else combine(f(a), mapReduce(f, combine, base)(a + 1, b))
  }

  println(s"sum of 1 to 5 is ${mapReduce(x => x, (x: Int, y: Int) => x + y, 0)(1, 5)}")
  println(s"product of 1 to 5 is ${mapReduce(x => x, (x: Int, y: Int) => x * y, 1)(1, 5)}")
}

/**
  * Finding fixed points of a functions so square root also.
  */
object HigherOrderFunctionExample6 extends App {

  val tolerance = 0.0001

  def abs(x: Double): Double = if (x < 0) -x else x
  def isGoodEnough(x: Double, y: Double): Boolean = abs((x - y) / x) / x < tolerance
  def fixedPoint(f: Double => Double)(firstGuess: Double):Double = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isGoodEnough(guess, next)) next else iterate(next)
    }
    iterate(firstGuess)
  }

  def squareRoot(x:Double):Double= fixedPoint(y => (y + x / y) / 2)(1.0)
  def averageDump(f:Double => Double)(x:Double):Double = x + f(x) / 2
  def squareWithAverageDump(x:Double) = fixedPoint(averageDump(y => x / y))(1.0)

  println(s"fixed point of a function f(x) = 1 + x / 2 is ${fixedPoint(x => 1 + x / 2)(1.0)}")
  println(s"square root of 2 using fixed point algorithm is is ${squareRoot(2)}")
  println(s"square root of 2 using fixed point algorithm with averageDump function is is ${squareWithAverageDump(2)}")

}