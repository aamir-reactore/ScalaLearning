package collections.lists.sorts

class InsertSort {

  def insertSort[T](list: List[T])(implicit exp1: T => Ordered[T]): List[T] = list match {
    case Nil    => Nil
    case h :: t => iSort(h, insertSort(t))
  }

  def iSort[T](x: T, list: List[T])(implicit exp1: T => Ordered[T]): List[T] = list match {
    case Nil              => x :: Nil
    case h :: _ if x <= h => x :: list
    case h :: t           => {
      h :: iSort(x, t)
    }
  }
}

object InsertSort {
  def apply: InsertSort = new InsertSort()
}

object TestInsertSort extends App {
  val l1 = List(6, 5, 4, 3, 2, 1)
  println(s"Insert Sort1==>${InsertSort.apply.insertSort(l1)}")

  val l2 = List(6, 5, 3, 4, 1, 2)
  println(s"Insert Sort2==>${InsertSort.apply.insertSort(l2)}")

  val l3 = List(6, 5, 7, 3, 4, 8, 1, 2)
  println(s"Insert Sort3==>${InsertSort.apply.insertSort(l3)}")
}