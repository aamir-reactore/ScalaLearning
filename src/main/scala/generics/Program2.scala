package generics

/**
  * https://stackoverflow.com/questions/30365846/why-are-input-parameters-contravariant-in-methods
  * https://stackoverflow.com/questions/663254/why-doesnt-the-example-compile-aka-how-does-co-contra-and-in-variance-w
  * (above link explanation by Daniel Spiewak)
  */
class A
class B extends A
class C extends B
  object TestProb1 extends App {

  //type constraints
  class D[T <: B] // T must be a B. i.e. for any T, it is a B or a subclass of B.

  new D[B]() //(upper bound)
  new D[C]()
  //new D[A]() CTE (type arguments [generics.A] do not conform to class D's type parameter bounds [T <: generics.B]

  class E[T >: B] // T superclass of B or B must be a T. i.e. for any T, B must also be that same type.

  new E[B]() //(lower bound)
  new E[A]()

  //new E[C]() same error as above
}

trait X
trait Y extends X
trait Z extends Y
//variance annotations.(by default invariant)
class TestProb2 extends App {

  //covariance
  class LL[+T]
  val z:LL[Z] = new LL[Z]()

  //we can assign any A[T] to A[Y] as long as T <: Y. i.e if T is an Y, then any A[T] is an A[Y]
  val y:LL[Y] = z

  // we can assign any A[T] to A[X] as long as T <: X.
  val x1:LL[X] = y
  val x2:LL[X] = z

  //contravariance

  class B[-T]

  val bY:B[Y] = new B[Y]()
  //we can assign any B[T] to B[Y] as long as T >: Y. i.e if Y is a T, then B[Y] is an B[T]
  val byY:B[Y] = bY
  //we can assign any A[T] to A[Z} as long as T >: Z. i.e if Z is a T , then B[Z] is an B[T]
  val bZ:B[Z] = bY
  val BzZ:B[Z] = byY

}

trait Node[+B] {
  def prepend[U >: B](elem: U): Node[U]
}

case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
  def prepend[U >: B](elem: U) = ListNode[U](elem, this)
  def head: B = h
  def tail = t
}

case class Nil[+B]() extends Node[B] {
  def prepend[U >: B](elem: U) = ListNode[U](elem, this)
}

trait Bird
case class AfricanSwallow() extends Bird
case class EuropeanSwallow() extends Bird

object jj extends App {
  val africanSwallowList: ListNode[AfricanSwallow] = ListNode[AfricanSwallow](AfricanSwallow(), Nil())
  val birdList: Node[Bird] = africanSwallowList
  val x: Node[Bird] = birdList.prepend(new EuropeanSwallow) // becoz new EuropeanSwallow is a Bird itself so U >:B either B or its supertype, here its B
}