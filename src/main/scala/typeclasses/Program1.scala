package typeclasses

import scala.annotation.implicitNotFound

@implicitNotFound("No member of type class Duplicate in scope for ${T}")
trait Duplicate[A, B] {
  def duplicate(value: A):B
}

object Duplicate {

   implicit object DuplicateString extends Duplicate[String, String] {
      def duplicate(value: String): String = value.concat(value)
   }

  implicit val duplicateInt: Duplicate[Int, Int] = new Duplicate[Int, Int] {
    def duplicate(value: Int): Int = value * value
  }

  implicit val duplicateChar: Duplicate[Char, String] = new Duplicate[Char, String] {
    def duplicate(value: Char): String = value.toString + value.toString
  }

}

object DuplicateWriter {
  import Duplicate._

  def write[A,B](value: A)(implicit dup: Duplicate[A, B]) = dup.duplicate(value)

}

object DuplicateExample extends App {

  implicit val anotherDuplicateInt = new Duplicate[Int, Int] {
    def duplicate(value: Int): Int = value + value
  }
  println(DuplicateWriter.write("hello"))
  println(DuplicateWriter.write(10))
  println(DuplicateWriter.write(10)(Duplicate.duplicateInt))
  println(DuplicateWriter.write('c'))
  //println(DuplicateWriter.write(3.3))
}