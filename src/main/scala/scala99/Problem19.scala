package scala99

object ListRotation extends App {

  //val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')
  val l = List('a', 'b', 'c', 'd', 'e')


  def test[T](list: List[T], index: Int): List[T] = (index, list) match {
    case (_, Nil) => throw new Exception("list is empty")
    case (0, _) => list
    case (n, list@(h :: tail)) => {
      if (index > 0) {
        test(tail :+ h, n - 1)
      } else {
        test(list.last :: list.init, n + 1)
      }
    }
  }

  def f1[T](list: List[T], n: Int): List[T] = {
    val l = list.size
    val i = if (list.nonEmpty) n % l else 0
    val (a, b) = list.splitAt(if (i < 0) l + i else i)
    b ::: a
  }


  //println(test(l, -2))
  println(test(l, 2))
  //println(f1(l, -3))
  // println(abc(List(1,2,3,4,5,6)))
}