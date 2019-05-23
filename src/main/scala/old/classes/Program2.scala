package old.classes

object P2A extends App {
  //val x1 = new Broker(1, 2) CTE
  val x2 = Broker(1,2)
  val x1 = new R(100,200)
  println(x1.x)
}
class Broker private(num1:Int, num2: Int)
  /**
    * To avoid making any more field in body of primary constructor we follow this approach
    * */
object Broker {
  def apply(num1:Int, num2:Int): Broker = {
    //create some local variable and do some calculations
    new Broker(num1, num2)
  }
}
// there is one more way to create local variable inside constructor body

class R(n: Int, d: Int) {
  val (x, y) = {
    val g = gcd
    (n/g, d/g)
  }
  def gcd = 10
  if((2 / 2 == 1) || (11+2 == 0)) {
    true
  } else {

  }
}

