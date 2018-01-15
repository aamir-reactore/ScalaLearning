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

  def usingTailRecursion[T](list: List[T], n: Int, x: T): List[T] = {
    def insertElement(list: List[T], n: Int, acc: List[T]): List[T] = (n, list) match {
      case (0, li) => acc.reverse ::: x :: li
      case (na, h :: tail) => insertElement(tail, na - 1, h :: acc)
      case (_, Nil) => throw new NoSuchElementException("list is empty")
    }
    insertElement(list, n, Nil)
  }

  println(s"insert using split          ==>${usingSplit(l, 3, 'z')}")
  println(s"insert using take drop      ==>${usingTakeDrop(l, 3, 'z')}")
  println(s"insert using tail recursion ==>${usingTailRecursion(l, 3, 'z')}")

}