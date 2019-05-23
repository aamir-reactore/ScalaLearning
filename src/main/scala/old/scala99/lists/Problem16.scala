package scala99.lists

//remove nth occurance inside list as multiple of n
object RemoveNthMultipleList extends App {

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
  println(s"remove elements at = ${usingZip(l, 5)}")
  println(s"remove elements at = ${tailRecursive(l, 3)}")

 /* def f4[T](n: Int, list: List[T]): List[T] = {
    //here stream will have a starting value of 1, so it comes as 1 at starting for it i.e (1, now it as 1 and evaluate if else)
    val cycle: Stream[Int] = Stream.iterate(1) { it =>
      println(s"it is $it")
      println(s"n is $n")
      if (it < n)  it + 1 else  it
    }
    println(s"head of stream ${cycle.head}")
    println(s"zipping result = ${list.zip(cycle)}")
    list.zip(cycle).filter(_._2 < n).map(_._1)
  }
  println(f4(3,l))*/

}