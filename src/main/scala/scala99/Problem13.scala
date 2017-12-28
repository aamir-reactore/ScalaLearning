package scala99
//P10 directly without using utility methods
object P13 extends App {

    val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e', 'f')

     def usingSpan[T](l:List[T]) = {
       def runEncoding[T](l:List[T], tempList:List[(Int, Any)]):List[(Int, Any)]= l match {
         case Nil => tempList.reverse
         case h :: _ => {
           val (group, list) = l.span(h == )
           val x = (group.length, h) :: tempList
           runEncoding(list, x)
         }
       }
       runEncoding(l,Nil)
     }

  println("direct length encoding = " + usingSpan(l))
}