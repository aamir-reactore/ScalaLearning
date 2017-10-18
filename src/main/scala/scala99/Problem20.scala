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

  def removeUsingDropTake[T](list:List[T], index:Int)  = {
    (l.take(index) ++ l.drop(index + 1),list(index))
  }
  def usingSplitAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case (Nil, _) if n == 0 =>
       println("it came here")
      throw new NoSuchElementException
    case (pre, e :: post)  =>
      println(s"nope it came here,pre is $pre and e is $e and post is $post")
      (pre ::: post, e)
    case (_, Nil)        => throw new NoSuchElementException
  }

  //println(s"partition using tail recursion => ${usingTailRec(l, 2)}")
  //println(s"partition using head tail      => ${usingDropTake(l, 2)}")
  println(s"partition using split          => ${usingSplitAt(0,l)}")

}