package martinoderskybook.basictypesandoperations

/**
  * Since there is no 2.abs, we will use implicits to create one
 */

object MathUtils {
  implicit class AbsoluteResolver(num:Double) {
    require(num < 0, "please provide a negative number")
    def absolute:Double = num.unary_-
  }
}

object MathUtilTest1 extends App {
  import MathUtils._
  println( -2.7.absolute )
}

