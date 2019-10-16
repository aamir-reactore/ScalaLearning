package caseclasses

import scala.annotation.tailrec


//lets create + of doubles using types but actually runtime they are just numbers
case class Add(value: Double) extends AnyVal {
  def +(m: Add) = Add(value + m.value)
}

object AddRun extends App {

  val a1 = Add(12)
  val a2 = Add(8)
  val a3: Add = a1 + a2
  println(a1 + a2)

}

object jj extends App {
  def f(n: Int):Int = {
    @tailrec
    def loop(acc:Int, n:Int):Int = {
      if(n == 1) acc else loop(acc * n, n - 1)
    }
    loop(1, n)
  }
  println( f(5) )
}