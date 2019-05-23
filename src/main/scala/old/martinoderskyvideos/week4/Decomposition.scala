package martinoderskyvideos.week4

trait Expr1 {
  def isNumber:Boolean
  def isSum:Boolean
  def numValue:Int
  def leftOpt:Expr1
  def rightOpt:Expr1
}
class Number1(n:Int) extends Expr1 {
  override def isNumber: Boolean = true
  override def isSum: Boolean = false
  override def numValue: Int = n
  override def leftOpt: Expr1 = throw new Error("Number1.leftOp")
  override def rightOpt = throw new Error("Number1.rightOp")
}
class Sum1(e1:Expr1,e2:Expr1) extends Expr1  {
  override def isNumber: Boolean = false
  override def isSum: Boolean = true
  override def numValue: Int = throw new Error("Sum1.numValue")
  override def leftOpt: Expr1 = e1
  override def rightOpt: Expr1 = e2
}

object Expr1Test extends App {

  def eval(e:Expr1): Int = {
    if(e.isNumber) e.numValue
    else if(e.isSum) eval(e.leftOpt) + eval(e.rightOpt)
    else throw new Error(s"Unknown expression $e")
  }
  val sum1 = new Sum1(new Number1(2),new Number1(4))
  println(eval(sum1))
}

trait Expr2 {
  def numValue:Int
  def leftOpt:Expr2
  def rightOpt:Expr2
}
class Number2(n:Int) extends Expr2 {
  override def numValue: Int = n
  override def leftOpt: Expr2 = throw new Error("Number1.leftOp")
  override def rightOpt = throw new Error("Number1.leftOp")
}
class Sum2(e1:Expr2,e2:Expr2) extends Expr2  {
  override def numValue: Int = throw new Error("Sum1.numValue")
  override def leftOpt: Expr2 = e1
  override def rightOpt: Expr2 = e2
}
object Expr2Test extends App {
  def eval(e:Expr2): Int = {
      if(e.isInstanceOf[Number2]) e.asInstanceOf[Number2].numValue
      else if(e.isInstanceOf[Sum2]) eval(e.asInstanceOf[Sum2].leftOpt) + eval(e.asInstanceOf[Sum2].rightOpt)
      else throw new Error(s"Unknown expression $e")
  }
  val sum1 = new Sum2(new Number2(2),new Number2(4))
  println(eval(sum1))
}
