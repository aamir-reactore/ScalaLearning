import SelfRecursiveTypes2.Doubler

/**
  *Source => http://www.alessandrolacava.com/blog/scala-self-recursive-types/
  */
object SelfRecursiveTypes1 extends App {

 trait Doubler[T] {
   def double:T
 }

  case class Square(base:Double) extends Doubler[Square] {
    override def double: Square = Square(base * 2)
  }

}
object SelfRecursiveTypes2 extends App {
  trait Doubler[T] {
    def double:T
  }
  case class Person(firstname: String, lastname: String, age: Int)

  //outrageous, avoid something like that by enforcing a compile-time check.
  case class Square(base:Double) extends Doubler[Person] {
    override def double: Person = Person("aamir","fayaz",28)
  }

}
//CTE
/*object SelfRecursiveTypes3 extends App {
  trait Doubler[T <: Doubler[T]] {
    def double: T
  }
  case class Person(firstname: String, lastname: String, age: Int)

  //outrageous, avoid something like that by enforcing a compile-time check.
  case class Square(base:Double) extends Doubler[Person] {
    override def double: Person = Person("aamir","fayaz",28)
  }
}*/

object SelfRecursiveTypes4 extends App {

  trait Doubler[T <: Doubler[T]] {
    def double: T
  }

  case class Square(base:Double) extends Doubler[Square] {
    override def double: Square = Square(base * 2)
  }
}
