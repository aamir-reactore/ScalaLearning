package martinoderskyvideos.week4

trait Expr
case class Number(n:Int) extends Expr //automatically gets companion object with factory method apply i.e, object Number {def apply(n: Int) = new Number(n)}
case class Sum(e1:Expr,e2:Expr) extends Expr

object ExprTest extends App {
  val exp = Sum(Number(1), Number(2))
  def eval(e:Expr):Int = e match {
    case Number(n)   => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }
  println( eval(exp) )
}

