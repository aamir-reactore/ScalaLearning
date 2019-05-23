package implicits.numeric

object IntToNumericTest1 extends App {
  import Numeric.Implicits._
  implicit def intToNumericT[T](x:Int)(implicit n:Numeric[T]):T = n.fromInt(x)
  def f[T:Numeric](a:T) = {
    a * 2
  }

  val result: Int = f[Int](10)
  println(result)
}

object ImplNumeric {
  implicit class NumericInt(x: Int) {
    def nu[T](implicit n: Numeric[T]) = n.fromInt(x)
  }
}


object IntToNumericTest2 extends App {

  import ImplNumeric._
  import Numeric.Implicits._

  def f[T:Numeric](a:T) = a * 2.nu

  val result: Int = f[Int](10)
  println(result)

}