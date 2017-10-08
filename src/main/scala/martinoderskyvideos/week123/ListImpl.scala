package martinoderskyvideos.week123

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
  def printCons[T](cons: List[T]): Unit = {
    if (cons.tail.isEmpty) {
      print(cons.head + " ")
    } else {
      print(cons.head + " ")
      printCons(cons.tail)
    }
  }
}

object List1 extends App {

  val list: Cons[Int] = new Cons(2, new Cons(4, new Cons(6, new Cons(8, new Nil))))
  println(list.isEmpty)
  println(list.head)
  println("printing cons")
  list.printCons(list)
  def getElementAtIndex[T](list: List[T], index: Int): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException("Index out of bound")
    if (index == 0) list.head else getElementAtIndex(list.tail, index - 1)
  }

  //println(getElementAtIndex(list, 10))
}

object List2FunctionObject extends App {
  def apply[T](x1: T, x2: T) = new Cons[T](x1, new Cons(x2, new Nil[T]()))

  def apply[T]() = new Nil

  println(apply[Int](1, 2))
}

