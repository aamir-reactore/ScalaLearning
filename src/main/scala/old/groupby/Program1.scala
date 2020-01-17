package groupby

object Program1 extends App {

   val l = List("Red", "Blue","Yellow","Red","Blue","Red","Yellow","Yellow","Red","Yellow","Red")

   val r= l.groupBy(identity)//.map(x => (x._1, x._2.length))

   println("Input list = " + l)
   println("output = " + r)

   val list = List('a','a','b','b','c','c','a','a','d')
   val res = list.foldLeft(List(list.head)) { (acc, elem) =>
     if(elem == acc.head) acc else elem :: acc
   }.reverse
   println(res)

}