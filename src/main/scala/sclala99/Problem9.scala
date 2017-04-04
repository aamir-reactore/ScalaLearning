package sclala99

//Pack consecutive duplicates of list elements into sublists.
object Problem9 extends App {


  val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')

  def subListDuplicates[T](l: List[T]) = {
    def subList[T](l: List[T], tempList: List[List[T]]): List[List[T]] = l match {
      case Nil => tempList.reverse
      case h :: tail => subList(tail.dropWhile(_ == h), List(l.takeWhile(_ == h)) ::: tempList)
    }
    subList(l, List[List[T]]())
  }

  def usingSpan[T](l: List[T]) = {
    def subList[T](l: List[T], tempList: List[List[T]]):List[List[Any]] = l match {
      case Nil => tempList
      case h :: _ => {
        val (same, _)  = l.span(_ == h)
        subList(l.filterNot(_ == h),  same :: tempList)
      }
    }
    subList(l, List[List[T]]())
  }

  println(s"sublisting duplicates = ${subListDuplicates(l)}")
  println(s"sublisting duplicates = ${usingSpan(l)}")

}