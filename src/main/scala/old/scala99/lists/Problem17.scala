package scala99.lists

//split a list
object SplitList extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')

  def usingSplitFunction[T](list:List[T], n:Int): (List[T], List[T]) =
     list.splitAt(n) //1 not 0 based


  def usingTakeDrop[T](list: List[T], n:Int): (List[T], List[T]) =
    (list.take(n),list.drop(n))


  def usingTailRecursion[T](list:List[T], n:Int): (List[T], List[T]) = {
     def recursive[T](l:List[T], resList:List[T], n: Int):(List[T], List[T]) = (n,l) match {
       case(_, Nil) => (resList.reverse, l)
       case (0, _) => (resList.reverse, l)
       case (_, h::tail) => recursive(tail, h :: resList, n-1)
     }
    recursive(list,Nil, n)
  }
  println(s"splitting using split function = ${usingSplitFunction(l,3)}")
  println(s"splitting using take drop = ${usingTakeDrop(l,3)}")
  println(s"splitting using tail recursion= ${usingTailRecursion(l,5)}")

}