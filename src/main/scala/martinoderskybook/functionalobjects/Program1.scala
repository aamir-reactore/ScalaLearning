package martinoderskybook.functionalobjects

object ll extends App {

  println(s"x is ${`while`(3)}") //backtick character
  def `while`(x:Int) = x
}