/*

package old.binarytrees

abstract sealed class Tree[+A](implicit exp: A => Ordered[A]) {
  def value: A

  def left: Tree[A]

  def right: Tree[A]

  def size: Int

  def isEmpty: Boolean

  def fail(errMsg: String): Nothing = throw new NoSuchElementException(errMsg)
  /**
    * Adds given element 'x' into this tree.
    *
    * Time - O(log n)
    * Space - O(log n)
    */
  def add[B >:A](b: B)(implicit ev$1: B => Ordered[B]): Tree[B] = {
    if (this.isEmpty) Tree.make(b)
    else if (b < this.value) Tree.make(value, left.add(b), right)
    else if (b > value) Tree.make(value, left, right.add(b))
    else this
  }

  def isBSTValid: Boolean = {
    if (isEmpty) true
    else if (left.isEmpty && right.isEmpty) true
    else if (left.isEmpty) right.value >= value && right.isBSTValid
    else if (right.isEmpty) left.value <= value && left.isBSTValid
    else right.value >= value && left.value <= value && left.isBSTValid && right.isBSTValid
  }

  def inOrderTraversal: String = {
    if (isEmpty) "." else "{" + left.inOrderTraversal + value + right.inOrderTraversal + "}"
  }

  def postOrderTraversal: String = {
    if (isEmpty) "." else "{" + left.inOrderTraversal + value + right.inOrderTraversal + "}"
  }

  def min: A = {
    def loop(t: Tree[A], value: A): A = {
      if (t.isEmpty) value else loop(t.left, t.value)
    }

    if (isEmpty) fail("An empty tree.")
    else loop(left, value)
  }

  def max: A = {
    def loop(t: Tree[A], value: A): A = {
      if (t.isEmpty) value else loop(t.right, value)
    }

    if (isEmpty) fail("An empty tree.")
    else loop(right, value)
  }

  def fold[B](n: B)(f: (B, A) => B) = {
    def loop(t: Tree[A], base: B): B = {
      if (t.isEmpty) n else loop(t.right, f(loop(t.left, n),value))
    }
    loop(this, n)
  }

}


object Leaf extends Tree[Nothing] {

  def value: Nothing = fail("An empty tree.")

  def left: Tree[Nothing] = fail("An empty tree.")

  def right: Tree[Nothing] = fail("An empty tree.")

  def size: Int = 0

  def isEmpty: Boolean = true

}

case class Branch[A](value: A, left: Tree[A] = Leaf,
                     right: Tree[A] = Leaf,
                     size: Int)(implicit ev$1: A => Ordered[A]) extends Tree[A] {
   def isEmpty: Boolean = false
}

object Tree {
  def empty[A]: Tree[A] = Leaf

  def make[A](value: A, left: Tree[A] = Leaf, right: Tree[A] = Leaf)(implicit ev$1: A => Ordered[A]) =
    Branch[A](value, left, right, left.size + right.size + 1)

  def apply[A](xs: A*)(implicit ev$1:A => Ordered[A]): Tree[A] = {
    xs.foldLeft(Tree.empty[A])((tree, item) => tree.add(item))
  }
}

object TreeTest1 extends App {
  //making an empty tree
   val emptyTree: Tree[Int] = Tree.empty[Int]
   println(emptyTree.size)
   println(emptyTree.isEmpty)
}

object TreeTest2 extends App {
  val tree = Tree(31,10,40,5,20,35,50)
}
*/
