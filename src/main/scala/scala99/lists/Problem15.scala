package scala99.lists

import scala.io.StdIn

//duplicate elements of b.a list by number specified
object DuplicateListRecursive extends App {

  val l = List('a','b','c')
  println("Enter duplication index..")
  val n = StdIn.readInt()

  val res1 = l.flatMap{ x => List.fill(n)(x)}
  println(res1)

  //using foldRight

  def usingFoldRight[T](l :List[T], n:Int):List[T] =
    l.foldRight(List[T]())(prepend(n, _, _))


  def prepend[T](n:Int, elem:T, accu:List[T]):List[T] =
        if(n == 0) accu else elem :: prepend(n - 1, elem, accu)

  println(usingFoldRight(l, n))

}