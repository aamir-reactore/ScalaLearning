package functions.partiallyappliedfunction

object Example1 extends App {

  val multiply = (a: Int, b: Int, c: Int) => a * b * c

  // less arguments passed
  val f: Int => Int = multiply(1, 2, _: Int)

  println(f(6))
}

object Example2 extends App {

  def Book(discount: Double, costprice: Double): Double = {
    (1 - discount/100) * costprice
  }

  val discountedPrice = Book(25, _: Double)

  // Displays discounted price
  // of the book
  println(discountedPrice(400))
}

object Example3 extends App {

  def Mul(x: Double, y: Double): Double = {
    x * y
  }

  val r: (Double, Double) => Double = Mul _

  // Displays Multiplication
  println(r(9, 8))
}

object Example4 extends App {

  def div1(x: Double, y: Double): Double = {
    x / y
  }

  def div2(x: Double, y: Double, z: Double): Double = {
    x + y + z
  }

  // applying currying approach
  val partiallyAppliedFunction:Function2[Double, Double, Double] = div1 _
  val partiallyAppliedFunction1:(Double, Double) => Double = div1 _
  val m: Double => (Double => Double) = partiallyAppliedFunction.curried
  val n: Double => (Double => (Double => Double)) = (div2 _).curried


  // Displays division
  println(m(72)(9))
}