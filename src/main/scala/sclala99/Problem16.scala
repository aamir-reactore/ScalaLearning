package sclala99

object P16 extends App {
  val l = List(1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15,16)

   def usingZip(l:List[Int], index:Int):List[Int] = {
     l.zipWithIndex.filterNot { x =>
       (x._2 + 1) % index == 0
     }.map(_._1)
   }

  def tailRecursive(l:List[Int], index:Int):List[Int] = {
    def removeIndexElement(n:Int, list:List[Int], resultList:List[Int]):List[Int] = (n, list) match {
      case (_, Nil) => resultList.reverse
      case (1, _ :: tail) => removeIndexElement(index, tail, resultList)
      case (c, h :: tail) => removeIndexElement(c - 1, tail, h :: resultList)
    }
    removeIndexElement(index,l,Nil)
  }
  println(s"remove elements at = ${usingZip(l, 3)}")
  println(s"remove elements at = ${tailRecursive(l, 3)}")
}