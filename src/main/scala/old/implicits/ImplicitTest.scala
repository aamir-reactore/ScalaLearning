package implicits
import scala.Predef
object ImplicitTest1 extends App {
  val j = 9.8
  println(j)
  implicit def doubleToInt(x:Double)=x.toInt
  val i:Int = 3.5
  println(i)
}