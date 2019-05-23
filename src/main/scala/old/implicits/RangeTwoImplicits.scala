package implicits


object AgeRange {
  implicit class range(age: Int) { //implicit class must have a primary constructor with exactly one argument in first parameter list
    def between(age1: Int, age2: Int): Boolean = age >= age1 && age <= age2
  }
}

object RangeTwoImplicits extends App {

   import AgeRange._
  println(22.between(12, 30))
}