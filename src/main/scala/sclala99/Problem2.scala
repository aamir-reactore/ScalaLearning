package sclala99

// print penultimate element of a list
object P2 extends App {

  val l = List(1, 2, 3, 4, 5, 6)
  println("penultimate element of list is = " + l.takeRight(2).head) //list.init.lastOption.getOrElse(throw new NoSuchElementException())

  def recursivePenultimate[T](l: List[T]): T = l match {
    case h :: _ :: Nil => h // List(x, _)
    case List(_) => throw new NoSuchElementException("Singleton list")
    case _ :: tail => recursivePenultimate(tail)
    case _ => throw new NoSuchElementException
  }

  println("penultimate element of list using tail rec. is = " + recursivePenultimate(l))
  println("penultimate element of list is = ")
}