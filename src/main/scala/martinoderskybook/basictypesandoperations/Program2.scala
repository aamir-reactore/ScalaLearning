package martinoderskybook.basictypesandoperations

/**
  * Since there is no 2.abs, we will use implicits to create one
 */

object MathUtils {
  implicit class AbsoluteResolver(num:Int) {
    def abs:Int = num.unary_-
  }
}

