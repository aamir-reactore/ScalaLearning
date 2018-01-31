package datastructures.queue

import scala.collection.immutable.Queue

/*class Queeue[+A](in: List[A] = Nil, out: List[A] = Nil) {

  def isEmpty: Boolean = (in, out) match {
    case (Nil, Nil) => true
    case (_, _)     => false
  }
  def dequeue:(A,Queue[A]) = out match {
    case hd :: tl => (hd, new Queue(in,tl))
  }
}*/

object QueueTest extends App{
  val p = Queue(1)
}