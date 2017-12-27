package collections.views


/**
  * Resources
  * https://stackoverflow.com/questions/6799648/in-scala-what-does-view-do
  * http://www.scala-lang.org/docu/files/collections-api/collections_42.html
  * Scala docs:- http://docs.scala-lang.org/overviews/collections/views.html
  */
object ViewFailTest1 extends App {
  val l = (1 to 1000000000).filter(_ % 2 == 0).take(10).toList
  println(l)
}
object ViewPassTest1 extends App {
  val l = (1 to 1000000000).view.filter(_ % 2 == 0).take(10).toList
  println(l)
}

object ViewFailTest2 extends App {
  def isPalindrome(x: String) = x == x.reverse
  def findPalidrome(s: Seq[String]) = s find isPalindrome
  val words = Array.fill(2000000000)("hello")
}