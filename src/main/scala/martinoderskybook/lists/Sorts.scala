/**
  * Insert Sort
  */

class InsertSort {
  def iSort[T](xs: List[T])(implicit ev$1: T => Ordered[T]): List[T] = xs match {
    case Nil    => Nil
    case h :: t => insert(h, iSort(t))
  }

  def insert[T](x: T, xs: List[T])(implicit ev$1: T => Ordered[T]): List[T] = xs match {
    case Nil    => x :: Nil
    case h :: t => if (x <= h) x :: xs else {
      h :: insert(x, t)
    }
  }

}
object InsertSort {
  def apply: InsertSort = new InsertSort()
}

object TestInsertSort extends App {
  val l1 = List(6,5,4,3,2,1)
 println(s"Insert Sort1==>${InsertSort.apply.iSort(l1)}")

  val l2 = List(6,5,3,4,1,2)
  println(s"Insert Sort2==>${InsertSort.apply.iSort(l2)}")
}