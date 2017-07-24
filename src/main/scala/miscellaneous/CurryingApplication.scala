package miscellaneous

/**
  * https://stackoverflow.com/documentation/scala/1636/currying#t=201707240343042457183
  *
  *
  * val f: (A, B) => C to val curriedF: A => B => C or val curriedF: A => (B => C)
  */
object CurryingApplication1 extends App {

  val totalYearlyIncome:(Int,Int) => Int =  (income, bonus) => income + bonus
  val totalYearlyIncomeCurried: Int => (Int => Int) = totalYearlyIncome.curried

  val partialTotalYearlyIncome: Int => Int = totalYearlyIncomeCurried(10000)

  partialTotalYearlyIncome(100)


}
object CurryingApplication2 extends App {

  class CarWheelsFactory {
    def applyCarWheels(carManufacturing:(String,String) => String): String => String =
      carManufacturing.curried("applied wheels..")
  }

  class CarBodyFactory {
    def applyCarBody(partialCarWithWheels: String => String): String = partialCarWithWheels("applied car body..")
  }

  val carWheelsFactory = new CarWheelsFactory()
  val carBodyFactory   = new CarBodyFactory()

  val carManufacturing:(String,String) => String = (wheels, body) => wheels + body

  val partialCarWheelsApplied: String => String  = carWheelsFactory.applyCarWheels(carManufacturing)
  val carCompleted = carBodyFactory.applyCarBody(partialCarWheelsApplied)
}
object CurryingApplication3 extends App {

  def minus(left: Int, right: Int): Int = left - right

  val numberMinus5: (Int) => Int = minus(_: Int, 5)
  val fiveMinusNumber: (Int) => Int = minus(5, _: Int)

  println( numberMinus5(7))    //  2
  println( fiveMinusNumber(7))
}

object CurryingApplication4 extends App {

  def curry[A, B, C](f:(A,B) => C):A => B => C = {
    (a: A) => (b: B) => f(a,b)
  }
  val g: (Int, Int) => Int = (x:Int, y:Int) => x + y
  val sum = curry(g)
  println( sum(1)(2) )
}

object CurryingApplication5 extends App {

  def sum(x:Int)(y:Int) = x+ y
  val res: (Int) => (Int) => Int = sum
   println(res(2)(4))
  val f: (Int, Int) => Int = Function.uncurried(res)
  println(f(2,3))
}
