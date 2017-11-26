package martinoderskybook.functionalobjects

object ll extends App {
      //literal identifier
  println(s"x is ${`while`(3)}") //backtick character
  def `while`(x:Int) = x

  //CTE val x_:Int = 10, give space between _ and:
  val x_ :Int = 90
  println(x_)
}