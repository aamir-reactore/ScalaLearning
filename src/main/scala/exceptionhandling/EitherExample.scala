package exceptionhandling

object EitherExample extends App {

  def divide(a: Double, b: Double): Either[String, Double]  = {
    Either.cond(b != 0, a / b, "b must not be zero")
  }

  println(divide(4, 0))
}