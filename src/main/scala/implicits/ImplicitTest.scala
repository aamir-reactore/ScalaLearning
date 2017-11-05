package implicits
import scala.Predef
object ImplicitTest1 extends App {
  implicit def doubleToInt(x:Double)=x.toInt
  val i:Int = 3.5
}