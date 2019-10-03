object Test1 extends App {


  val x  = List("A" -> List(10, 12), "B" -> List(101, 122), "C" -> List(1440, 1442))
  println(x)
  val y = x.toMap
  println(y)
}