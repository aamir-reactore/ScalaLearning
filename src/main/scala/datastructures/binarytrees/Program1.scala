package datastructures.binarytrees

abstract sealed class Tree[+A] {
  def value: A

  def left: Tree[A]

  def right: Tree[A]

  def size: Int

  def isEmpty: Boolean

  def fail(errMsg: String): Nothing = throw new NoSuchElementException(errMsg)

  def add[B >: A](b: B)(implicit ev$1: B => Ordered[B]): Tree[B] = {
    if (isEmpty) Tree.make(b)
    else if (b < value) Tree.make(value, left.add(b), right)
    else if (b > value) Tree.make(value, left, right.add(b))
    else this
  }

}


object Leaf extends Tree[Nothing] {

  def value = fail("An empty tree.")

  def left = fail("An empty tree.")

  def right = fail("An empty tree.")

  def size = 0

  def isEmpty = true

}

case class Branch[A](value: A, left: Tree[A] = Leaf,
                     right: Tree[A] = Leaf,
                     size: Int) extends Tree[A] {
  override def isEmpty: Boolean = false
}

object Tree {
  def empty[A]: Tree[A] = Leaf

  def make[A](value: A, left: Tree[A] = Leaf, right: Tree[A] = Leaf) =
    Branch(value, left, right, left.size + right.size + 1)

  def apply[A](xs: A*)(implicit ev$1: A => Ordered[A]): Tree[A] = {
    xs.foldLeft(Tree.empty[A])((tree, item) => tree.add(item))
  }
}