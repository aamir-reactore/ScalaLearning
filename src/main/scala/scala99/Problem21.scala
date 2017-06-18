package scala99

//insert user-input element at user-input position in list, assume 0 based
object Problem21Sol extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g')

  def usingSplit[T](list: List[T], n: Int, x: T): List[T] = {
    val (pre, post) = list.splitAt(n)
    pre ::: x :: post
  }

  def usingTakeDrop[T](list: List[T], n: Int, x: T): List[T] =
    list.take(n) ::: x :: list.drop(n)

  def usingTailRecursion[T](list: List[T], n: Int, x: T, acc: List[T]): List[T] = (n, list) match {
    case (0, _ :: tail) => acc ::: x :: tail
    case (n, h :: tail) => usingTailRecursion(tail, n - 1, x, h :: acc)
    case (_, Nil) => throw new NoSuchElementException("list is empty")
  }

  println(s"insert using split          ==>${usingSplit(l, 3, 'z')}")
  println(s"insert using take drop      ==>${usingTakeDrop(l, 3, 'z')}")
  println(s"insert using tail recursion ==>${usingTakeDrop(l, 3, 'z')}")
}