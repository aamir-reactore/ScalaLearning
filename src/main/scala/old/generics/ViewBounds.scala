package generics

object ViewBoundTest1 extends App {
  implicit def strToInt(x: String) = x.toInt
  val y: Int = "123"
  val res: Int = math.max("123",3)

  class Container[A <% Int] { def addIt(x: A) = 123 + x }

  println( new Container[String].addIt("2") )
  println( new Container[Int].addIt(2) )
}