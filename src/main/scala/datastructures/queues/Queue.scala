package datastructures.queues

/**
  * Enqueue - O(1)
  * Dequeue - O(1)
  * Front - O(1)
  * Rear - O(1)
  */
class Queue[+A](in: List[A] = Nil, out: List[A] = Nil) {
  
  def isEmpty: Boolean = in.isEmpty && out.isEmpty
  def front:A =  dequeue match { case (a, _) => a }
  def rear:Queue[A] = dequeue match {case (_,a) => a}
  def enqueue[B >: A](a: B): Queue[B] = new Queue(a :: in, out)
  def dequeue:(A,Queue[A]) = out match {
    case h :: t => (h , new Queue(in,t))
    case Nil => in.reverse match {
      case h :: t => (h, new Queue(Nil,t))
      case Nil => throw new NoSuchElementException("empty queue.")
    }
  }
  override def toString = (out ::: in.reverse).mkString("Queue(",",",")")

}

object Queue {
  def empty[A]: Queue[A] = new Queue()
  def apply[A](xs: A*): Queue[A] =
    xs.foldLeft(Queue.empty[A])((acc, x) => acc.enqueue(x))
}

object TestQueue extends App {
  val q: Queue[Int] = Queue[Int](1,2,3,4,5)
  println(q)
  val newQ: (Int, Queue[Int]) = q.dequeue
  println(newQ._2)
  println(">>")
  println(q.rear)
  println(q.front)
  println(">>")
  println(newQ._2.rear)
  println(newQ._2.front)

}