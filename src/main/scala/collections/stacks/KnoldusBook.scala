package collections.stacks

trait Stack[T] {
  def pop: (T, Stack[T])

  def push(e: T): Stack[T]

  def peek: T

  def isEmpty: Boolean

  def print: String
}

class StackImpl[T] extends Stack[T] {
  protected val dataElements: List[T] = Nil

  private def mkNewStack(es: List[T]): Stack[T] = new StackImpl[T] {
    override val dataElements: List[T] = es
  }

  def push(e: T): Stack[T] = mkNewStack(e :: dataElements)

  def pop: (T, Stack[T]) = (dataElements.head, mkNewStack(dataElements.tail))

  def peek = dataElements.head

  def isEmpty: Boolean = dataElements.isEmpty

  def print: String = dataElements.mkString("[", ",", "]")
}

object StackImplTest extends App {
  var stack: Stack[Int] = new StackImpl[Int]
  stack = stack.push(1)
  stack = stack.push(2)
  stack = stack.push(3)
  stack = stack.push(4)

  println("Stack is :" + stack.print)
}