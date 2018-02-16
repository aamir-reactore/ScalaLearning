package Implicits

object ImplicitConversionsP1 extends App {

  case class Foo(i: Int)

  // without the implicit
  val x1 = Foo(40) + 2 // compilation-error (type mismatch)

  // defines how to turn b.a Foo into an Int
  implicit def fooToInt(foo: Foo): Int = foo.i

  // now the Foo is converted to Int automatically when needed
  val x2 = 2 + Foo(30) // 42

  println(x1)
  Console.println(x2)

}