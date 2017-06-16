package scala99

//check if list is palindrome
object P6 extends App {

  val l = List(1, 2, 2, 1)
  println("is list palindrome = " + (l == l.reverse))

  val isPal = l.zip(l.reverse)
                .forall { case (a, b) => a == b }
  println("is list palindrome = " + isPal)

}
