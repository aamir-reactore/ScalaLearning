package scala99

// first index is 0 based and second one is 1 based (kinda like substring)
object SliceList extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')
   println(s"###test lis is = $l")
  def usingSlice[T](list:List[T], start:Int, end:Int):List[T] =
    list.slice(start, end)

   def usingDropTake[T](list:List[T], start:Int, end:Int):List[T] =
     list.drop(start).take(end - start)

  def usingZip[T](list:List[T], start:Int, end:Int): List[Char] =
     l.zip(1 to end).filter(_._2 > start).unzip._1

  def usingForYield[T](list:List[T], start:Int, end:Int): Seq[Any] =
    for((x, n) <- list.zip(1 to end) if n > start) yield x

  def usingFoldRight[T](l:List[T], start:Int, end:Int): List[Any] =
    l.zip(1 to end).foldRight(List[T]()){case ((elem,index), acc) => if(index > start) elem :: acc else acc}

  println(s"slicing with value start = 3 and end = 7, indexing like finding substring")
  println(s"slice using default method = ${usingSlice(l, 3, 7)}")
  println(s"slice using take drop method = ${usingDropTake(l, 3, 7)}")
  println(s"slice using zip method = ${usingZip(l, 3, 7)}")
  println(s"slice using for yield method = ${usingForYield(l, 3, 7)}")
  println(s"slice using fold right method = ${usingFoldRight(l, 3, 7)}")


  def f4[T](list: List[T], i: Int, j: Int, acc: List[T] = Nil): List[T] = {
    list match {
      case Nil => acc.reverse
      case x :: xs =>
        if (i > 0)
          f4(xs, i - 1, j - 1, acc)
        else if (j > 0)
          f4(xs, i, j - 1, x :: acc)
        else acc.reverse
    }
  }



  println(s"functional style => ${f4(l,3,70)}")

}