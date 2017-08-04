package martinoderskyvideos.week4

import martinoderskyvideos.week123.{EmptySet, NonEmptySet}

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend[U >: T](elem:U):List[U] = new Cons(elem,this)
}

object Nil extends List[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

object GenericsTest1 extends App {
  val n:List[String] = Nil

  def f(x1:List[NonEmptySet],x2: List[EmptySet]) = x1 prepend x2
}