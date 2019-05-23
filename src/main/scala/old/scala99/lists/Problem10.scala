package scala99.lists

// run length encoding of a sub-lists constructed in Problem9
object SubListEncodingLength extends App {

  val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')

  import SublistDuplicates._

  val res1 = subListDuplicates[Char](l).map(x => (x.length, x.head))
  val res2 = subListDuplicates[Char](l).foldRight(List[(Int, Char)]()) { (elem, acc) => (elem.length, elem.head) :: acc }
  val consecutiveList = subListDuplicates[Char](l)
  val res3 = consecutiveList.map(_.length) zip consecutiveList.map(_.head)

  println("Input list = " + l)
  println("Run-length encoding of list =" + res1)
  println("Run-length encoding of list =" + res2)
  println("Run-length encoding of list =" + res3)

}