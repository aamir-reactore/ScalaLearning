package martinoderskybook.basictypesandoperations

/**
  * Since there is no 2.abs, we will use implicits to create one
 */

object MathUtils {
  implicit class AbsoluteResolver(val num:Double) extends AnyVal {
    def absolute:Double = num.unary_-
  }
}

object MathUtilTest1 extends App {
  import MathUtils._
  println( -2.7.absolute )
}

