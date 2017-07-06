package Implicits

object ImplicitClassesP1 extends App {
  import StringUtil._
  println("Hello world".withoutVowels) // Hll wrld
}

object StringUtil {
  implicit class StringEnhancer(val str: String) extends AnyVal {
    def withoutVowels: String = str.replaceAll("[aeiou]", "")
  }
}

