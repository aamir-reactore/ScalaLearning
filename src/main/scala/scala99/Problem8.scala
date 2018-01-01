package scala99

object RemoveConsecutiveDuplicates extends App {

  val l = List[Char]('a', 'a', 'a', 'a', 'b', 'b', 'c', 'a', 'a', 'd', 'd')

  def removeConsecutiveDuplicates[T](list: List[T]): List[Char] = {
    def remove[T](list: List[T], tempList: List[T]): List[T] = list match {
      case Nil => tempList.reverse
      case h :: tail => remove(tail.dropWhile(_ == h), h :: tempList)
    }
    remove(l, Nil)
  }

  //using foldleft
  val foldingLeft = l.foldLeft(List(l.head)) { (acc, elem) =>
                   if(elem == acc.head) acc  else  elem :: acc
                }

  //using foldRight
  def  foldingRight[T]: List[T] => List[T] = {
    case Nil => Nil
    case l => l.foldRight(List(l.last)){(elem, acc) => if (elem == acc.head) acc else elem :: acc}
  }

  //using zip hats-off
  def usingZip[T]: List[T] => List[T] = {
    case Nil => Nil
    // @ => variable binding on pattern matching
    case xs@(x :: tail) => x :: xs.zip(tail).filter(p => p._1 != p._2).map(_._2)
  }

  println("list after removing consecutive dup. = " + removeConsecutiveDuplicates(l))
  println("using folding left = " + foldingLeft.reverse)
  println("using folding right = " + foldingRight(l))
  println("using unzip = " + usingZip(l))

}