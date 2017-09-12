package Implicits
object ImplicitExample extends App {

   def yo(implicit name:String) = println(s"yo $name")
   yo("aamir")
  implicit val oby = "oby"
  yo
  implicit val one =1
  yo
  //implicit val barney = "Barney"
  //yo
}
/*object ImplicitExample2 extends App {

  def yo(i:Int)(implicit name:String) = println(s"yo $name")
  yo(1)("aamir")
  implicit val oby = "oby"
  yo(1)
  implicit val one =1
  yo(1)
  yo(1)
}*/

/*
object ImplicitExample3 extends App {
  def method1(x:Int) = println(s"Integer value is $x")
  implicit def doubleToInt(d: Double) = d.toInt
  method1(42.0)
}
*/


/*object ImplicitExample4 extends App {

  case class Text(content: String)
  case class Prefix(text: String)

  def printText(text: Text): Unit = {
    println(text.content)
  }
  implicit def String2Text(content: String)(implicit prefix: Prefix) = {
    Text(prefix.text + " " + content)
  }
  implicit val prefixLOL = Prefix("Hello")
    printText("World!")

  // Best to hide this line somewhere below a pile of completely unrelated code.
  // Better yet, import its package from another distant place.
  //implicit val prefixLOL = Prefix("Hello")
}*/
