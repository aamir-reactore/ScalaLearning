package scala99.lists

// print penultimate element of a list
object SecondLastElement extends App {

  val list = List(1, 2, 3, 4, 5, 6)
  //println("penultimate element of list is = " + list.init.lastOption.getOrElse(throw new NoSuchElementException()))

  def recursivePenultimate[T](l: List[T]): T = l match {
    case h :: _ :: Nil => h // List(x, _)
    case List(_)       => throw new NoSuchElementException("Singleton list") // case List(_,_)  means list of 2 elements with any
    case _ :: tail     => recursivePenultimate(tail)
    case _             => throw new NoSuchElementException
  }

  println("penultimate element of list using tail rec. is = " + recursivePenultimate(list))
}