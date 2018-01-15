package datastructures.search

/**
  * Worst - O(n)
  * Best - O(1)
  * Average - O(n)
  **/
object LinearSearch1 extends App {

   def searchLinear[A](list:List[A],key:A):Option[A] = {
      def search(as:List[A]):Option[A] = {
        if(as.isEmpty) None
        else if(as.head == key) Some(as.head)
        else search(as.tail)
      }
     search(list)
   }

}