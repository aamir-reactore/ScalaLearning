package generics
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

  val ax:B[Y] = new B[Y]()
  //we can assign any B[T] to B[Y] as long as T >: Y. i.e if Y is a T, then B[Y] is an B[T]
  val ayx:B[Y] = ax
  //we can assign any A[T] to A[Z} as long as T >: Z. i.e if Z is a T , then B[Z] is an B[T]
  val azx:B[Z] = ax
  val azy:B[Z] = ayx

}

