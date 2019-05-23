package implicits.implicitly

object ImplicitlyTest1 extends App {
  class Parent
  class Child extends Parent
  implicit val b = new Child
  implicit val a = new Parent
  val res: Child = implicitly[Child] //Use implicitly to find out which implicit gets resolved.
}