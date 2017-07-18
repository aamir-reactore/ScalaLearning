package martinoderskyvideos

trait IntList[T] {
  def isEmpty:Boolean
  def head:T
  def tail:List[T]
}

class Nil[T] extends IntList[T] {
  override def isEmpty: Boolean = true
  override def head: T = throw new NoSuchElementException("Nil.head reached")
  override def tail: List[T] = throw new NoSuchElementException("Nil.tail reached")
}
class Cons[T](val head:Int, val tail:List[T]) extends IntList[T] {
  override def isEmpty: Boolean = false
}

object IntListImpl extends App {
  val list = new Cons[Int](3,)
}