package projecteuler

object NumberOfRoutes extends App {

  import Factorial._

  val res = 40.! / ((40 - 20).! * 20.!)
  println(res)
}

object Factorial {
  implicit class FactorialInt(val num:Int)  extends AnyVal {
    def ! : BigInt = (BigInt(1) to num).product
  }
}

object s extends App {
  println((BigInt(1) to BigInt(20)).product)
}

