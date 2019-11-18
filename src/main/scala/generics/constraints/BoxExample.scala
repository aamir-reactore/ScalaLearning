package generics.constraints

object BoxExample1 extends App {

  class X
  class Y extends X
  abstract class Fruit extends X { def name: String }
  class Orange extends Fruit { def name = "Orange" }
  class Apple extends Fruit { def name = "Apple" }

  class Box[+T] {
    def method[U >: T](p: U) = { /* here be code */ }
  }
  var apple: Apple = new Apple
  val orange: Orange = new Orange
  var box: Box[Fruit] = new Box[Orange]
  box.method(apple)
  box.method(orange)
  box.method(new X())
  box.method(new Y())

}

object BoxExample2 extends App {

  class X
  abstract class Fruit extends X { def name: String }
  class Orange extends Fruit { def name = "Orange" }
  class Apple extends Fruit { def name = "Apple" }

  class Box[+T] {
    def method[U >: T](p: U) = { /* here be code */ }
  }
  var apple: Apple = new Apple
  val orange: Orange = new Orange
  var orangeBox: Box[Orange] = new Box[Orange]

  //orangeBox.method(apple)
  orangeBox.method(orange)
  orangeBox.method(new X())

}