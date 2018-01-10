package scala99

object RemoveKthElementFromList extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g')

  def remoteKthUsingPartitionRec[T](list: List[T], index: Int): (List[T], T) = {
    def partitionTuple(list: List[T], acc: List[T], index: Int): (List[T], T) = {
      val h :: t = list
      if (index > 0) {
        partitionTuple(t, h :: acc, index - 1)
      } else {
        (acc.reverse ::: t, h)
      }
    }
    partitionTuple(list, Nil, index)
  }

  def removeAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, e :: post)  => (pre ::: post, e)
    case (_, Nil)        => throw new NoSuchElementException
  }

  //zero based index passed for this method
  def removeUsingDropTake[T](list:List[T], index:Int)  = {
    (l.take(index) ++ l.drop(index + 1),list(index))
  }

  println(s"using drop and take => ${removeUsingDropTake(l, 2)}")
  println(s"using tail recursion => ${remoteKthUsingPartitionRec(l, 2)}")
  println(s"using removeAt => ${removeAt(2, l)}")
}