package scala99

//Pack consecutive duplicates of list elements into sublists.
object SublistDuplicates extends App {


  val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')

  def subListDuplicates[T](l: List[T]) = {
    def subList[T](l: List[T], tempList: List[List[T]]): List[List[T]] = l match {
      case Nil       => tempList.reverse
      case h :: tail => subList(tail.dropWhile(_ == h), List(l.takeWhile(_ == h)) ::: tempList)
    }

    subList(l, List[List[T]]())
  }

  def usingSpan[T](l: List[T]) = {
    def subList[T](l: List[T], tempList: List[List[T]]): List[List[Any]] = l match {
      case Nil       => tempList.reverse
      case h :: tail => {
        val (same, rest) = tail.span(h ==)
        subList(rest, (h :: same) :: tempList)
      }
    }

    subList(l, List[List[T]]())
  }


  def f1[T]: List[T] => List[List[T]] = {
    case Nil    => Nil
    case h :: t =>
      val (a, b) = t.span(h == _)
      (h :: a) :: f1(b)
  }

  println(s"tail recursion sublisting duplicates = ${subListDuplicates(l)}")
  println(s"tail recursion with Span  duplicates = ${usingSpan(l)}")
  println(s"functional sublisting duplicates = ${f1(l)}")

}