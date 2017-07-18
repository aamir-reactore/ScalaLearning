package martinoderskyvideos

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

object List1 extends App {
  val list = new Cons(2, new Cons(4,new Cons(6, new Cons(8,new Nil))))
  println(list.isEmpty)
  println(list.head)

  def getElementAtIndex[T](list:List[T], index:Int):T = {
    if(list.isEmpty) throw new IndexOutOfBoundsException("Index out of bound")
    if(index == 0) list.head else getElementAtIndex(list.tail, index - 1)
  }
  println(getElementAtIndex(list,10))
}

