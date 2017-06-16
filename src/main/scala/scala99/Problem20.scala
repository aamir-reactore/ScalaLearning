package scala99

object P20 extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g')

  def usingTailRec[T](list: List[T], index: Int) = {
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

  def usingDropTake[T](list:List[T], index:Int) = {
    (l.take(index) ++ l.drop(index + 1),list(index))
  }

  def usingSplitAt[T](list:List[T], index:Int): (List[T], T) = list.splitAt(index) match {
    case (pre, h :: t) => (pre ::: t,h)
  }

  println(s"partition using tail recursion => ${usingTailRec(l, 2)}")
  println(s"partition using head tail      => ${usingDropTake(l, 2)}")
  println(s"partition using split          => ${usingDropTake(l, 2)}")

}