package old.implicits

object DoubleValidatorImplicitTest extends App {

  import DoubleValidator._

  var num = 3.2
  val res: Double = num.isValid
  println(num.isValid)
  num = Double.NaN
  println(num.isValid)
}

object DoubleValidator {

  implicit class Validator(val value: Double) extends AnyVal {
    def isValid: Double = if (value.isNaN) 0.0 else value
  }

}
