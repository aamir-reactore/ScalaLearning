package martinoderskyvideos.week4

/**
  * https://github.com/iirvine/functional-programming-in-scala/blob/master/notes/week%204/007-lists.md
  */
object ListTest1 extends App {
  //Convention: Operators ending in ":" associate to the right.
  val fruit1 = List("apples", "oranges", "pears")
  //above is syntactic sugar of
  val l = 1 :: 2 :: scala.Nil // because Nil is user defined in week123 package
   val fruit2 = "apples" :: "oranges" :: "pears" :: scala.Nil  //
  val fruit3 = "apples" :: ("oranges" :: ("pears" :: scala.Nil))
}