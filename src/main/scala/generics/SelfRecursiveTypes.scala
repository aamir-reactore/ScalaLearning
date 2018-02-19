import SelfRecursiveTypes2.Doubler

/**
  *Source => http://www.alessandrolacava.com/blog/scala-self-recursive-types/
  */

//F-bounded polymorphic types or Self Recursive Types
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

//still one problem
object SelfRecursiveTypes5 extends App {
  trait Doubler[T <: Doubler[T]] {
    def double: T
  }

  case class Square(base: Double) extends Doubler[Square] {
    override def double: Square = Square(base * 2)
  }

  case class Apple(kind: String) extends Doubler[Square] { //it should be Doubler[Apple]
    override def double: Square = Square(5)
  }


}

object SelfRecursiveTypes6 extends App {
  trait Doubler[T <: Doubler[T]] { self:T =>
    def double: T
  }

  case class Square(base: Double) extends Doubler[Square] {
    override def double: Square = Square(base * 2)
  }

 /* case class Apple(kind: String) extends Doubler[Square] { //CTE
    override def double: Square = Square(5)
  }*/

  case class Apple(kind: String) extends Doubler[Apple] { //it should be Doubler[Apple]
    override def double: Square = Square(5)
  }


}
