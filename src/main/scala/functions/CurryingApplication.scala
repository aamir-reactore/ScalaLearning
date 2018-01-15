package functions

/**
  * https://stackoverflow.com/documentation/scala/1636/functions.currying#t=201707240343042457183
  * val f: (A, B) => C to val curriedF: A => B => C or val curriedF: A => (B => C)
  */
object CurryingApplication1 extends App {
  val totalYearlyIncome:(Int,Int)   =>  Int  =  (income, bonus) => income + bonus
  val totalYearlyIncomeCurried: Int => (Int => Int) = totalYearlyIncome.curried
  val partialTotalYearlyIncome: Int =>  Int  = totalYearlyIncomeCurried(10000)
  partialTotalYearlyIncome(100)
}

object CurryingApplication3 extends App {

  def minus(left: Int, right: Int): Int = left - right

  val numberMinus5: (Int) => Int    = minus(_: Int, 5)
  val fiveMinusNumber: (Int) => Int = minus(5, _: Int)

  println( numberMinus5(7))    //  2
  println( fiveMinusNumber(7)) // -2
}

object CurryingApplication4 extends App {

  def curry[A, B, C](f:(A,B) => C):A => B => C = {
    (a: A) => (b: B) => f(a, b)
  }

  val g: (Int, Int)   => Int = (x, y) => x + y
  val sum: Int => Int => Int = curry(g)
  println( sum(1)(2) )

}

object CurryingApplication5 extends App {

  def sum(x:Int)(y:Int): Int = x + y
  val res: (Int) => (Int) => Int = sum
   println(res(2)(4))
  val f: (Int, Int) => Int = Function.uncurried(res)
  println(f(2,3))
  val x1: Int => Int => Int = f.curried
  val x2: Int => Int => Int = CurryingApplication4.curry(f)
}
