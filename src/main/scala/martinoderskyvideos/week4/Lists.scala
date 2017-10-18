package martinoderskyvideos.week4

/**
  * https://github.com/iirvine/functional-programming-in-scala/blob/master/notes/week%204/007-lists.md
  */
object ListTest1 extends App {
  //Convention: Operators ending in ":" associate to the right.
  val fruit1 = List("apples", "oranges", "pears")
  //above is syntactic sugar of
  val l = 1 :: 2 :: scala.Nil
  // because Nil is user defined in week123 package
  val fruit2 = "apples" :: "oranges" :: "pears" :: scala.Nil
  //
  val fruit3 = "apples" :: ("oranges" :: ("pears" :: scala.Nil))

  val list: scala.List[Nothing] = List()

  def insertSort(x: Int, list: scala.List[Int]): scala.List[Int] = list match {
    case scala.List() => scala.List(x)
    case head :: tail => if (x <= head) x :: list else head :: insertSort(x, tail)
  }

  def iSort(list: scala.List[Int]): scala.List[Int] = list match {
    case scala.Nil => scala.Nil
    case head :: tail => insertSort(head, iSort(tail))
  }
  println(s"sorted list is ${iSort(List(8,7,6,5,4,3))}")
}

//insertion sort kind of thing, sort tail and then insert head in proper place

