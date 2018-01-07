/**
  * Scala imports can not only import non-package members but
  * also packages memebers
  */

object AStartB extends App {
  import java.util.regex
  //import java.util.regex.Pattern
  val pat = regex.Pattern.compile("a*b")
  //val pat = Pattern.compile("a*b")
}