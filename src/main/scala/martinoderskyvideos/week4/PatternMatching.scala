package martinoderskyvideos.week4

/**
  * A case class automatically gets companion object with factory method apply
  * i.e, case class Number(n:Int) ==> object Number {def apply(n: Int) = new Number(n)}
  */
trait Expr
case class Number(n:Int) extends Expr
case class Sum(e1:Expr,e2:Expr) extends Expr
case class Var(x: String) extends Expr
case class Product(e1: Expr, e2: Expr) extends Expr

object ExprTest extends App {
  val exp = Sum(Number(1), Number(2))

  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }

  def show(e:Expr):String = e match {
    case Number(n) => n.toString
    case Sum(l,r) => s"${show(l)} + ${show(r)}"
  }

  println(eval(exp))
  println(show(exp))
}

object ExprTest2 extends App {

  val pro = Product(Number(4),Number(2))

  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Product(e1, e2) => eval(e1) * eval(e2)
  }

  def show(e:Expr):String = e match {
    case Var(n) => n
    case Product(l,r) => s"${show(l)} * ${show(r)}"
  }
  println(eval(pro))
  val varExp = Product(Var("2"),Var("4"))
  println(show(varExp))


}

object ExprTest3 extends App {
  def show(e:Expr):String = e match {
    case Var(n) => n
    case Number(n) => n.toString
    case Sum(l,r) => s"(${show(l)} + ${show(r)})"
    case Product(l,r) => s"${show(l)} * ${show(r)}"
  }
  println(show(Sum(Product(Number(2),Var("x")),Var("y"))))
  println(show(Product(Sum(Number(2),Var("x")),Var("y"))))
}