package implicits

object Example1 extends App {

  def add(x: Int)(implicit y: Int): Int = x + y
  //add(2) CTE, no implicit found for paramter y:Int
}

object Example2 extends App {
  def add(x: Int)(implicit y: Int): Int = x + y

  implicit val z1:Int = 10
  println( add(6) ) // using implicit variable 16
}




object Example3 extends App {


  def add(x: Int)(implicit y: Int): Int = x * y

  println( add(6) )

  implicit val z1:Int = 10

  /**
    * if implicit variable is defined after method call then, we will get a compiler warning
    * and passed value to method will be 0 (default value of Int), but it won't cause any
    * compile time error, and runs smoothly
    *
    **Warning:(9, 15) Reference to uninitialized value z1
    * println( add(6) )
    */


}

object Example4 extends App {

  def add(x: Int)(implicit y: Int): Int = x + y

  //uncomment below implicit variables to see the ouput error message
  //implicit val z1: Int = 10
  implicit val z2: Int = 12
  //CTE, ambiguous implicit values
  println( add(6) ) //CTE

}

object Example5 extends App {
  def alert(msg: String) = println(msg)

  //alert(70) //Type mismatch, expected: String, actual:Int
}

object Example6 extends App {

   def alert(msg: String) = println(msg)

  implicit def intToString(x: Int): String = x.toString // this should be before alert call

  alert(70)

}