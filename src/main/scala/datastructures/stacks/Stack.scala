package datastructures.stacks

class Stack[+A](self: List[A]) {

  def top: A = if (self.nonEmpty) self.head else throw new NoSuchElementException("top of empty stack")

  def rest: Stack[A] = new Stack(self.tail)

  def isEmpty: Boolean = self.isEmpty

  def pop: (A, Stack[A]) = if (self.nonEmpty) (top, rest) else throw new NoSuchElementException("pop of empty stack")

  def push[B>:A](b:B): Stack[B] = new Stack(b :: self)
}
object Stack {
  def empty[A]:Stack[A] = new Stack(Nil)
  def apply[A](xs:A*):Stack[A] = xs.foldLeft(Stack.empty[A])((acc,x) =>acc.push(x))
}

object StackTest extends App {

}
