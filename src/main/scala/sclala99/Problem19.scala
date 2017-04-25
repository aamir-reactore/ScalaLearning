package sclala99

object P19 extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')


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


  println(test(Nil, 100))
  // println(abc(List(1,2,3,4,5,6)))
}