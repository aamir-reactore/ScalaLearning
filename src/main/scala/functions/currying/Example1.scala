package functions.currying

object Example1 extends App {
  // transforming the function that
  // takes two(multiple) arguments into
  // a function that takes one(single) argument.
  def add2(a: Int): Int => Int = (b: Int) => a + b

    println(add2(20)(19))
}