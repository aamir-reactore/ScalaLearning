
val l = List(1,2,3)
l.take(3)
l.drop(3)

List(1).zip(List(1,2,3,4))
val rl = List(1,2,3,4,5,6,7,8) diff List(4,5,6,7,8)


/*import org.joda.time.DateTime

val dt = DateTime.now()
dt.withTimeAtStartOfDay()
dt.withTimeAtStartOfDay().plusDays(1).minusSeconds(1)*/


/*import scala.collection.immutable.{Nil, Queue}

object Queue {
  def empty[A]: Queue[A] = new Queue(Nil, Nil)
}

class Queue[A] private(in: List[A], out: List[A]) {
 self =>
  def isEmpty: Boolean = in.isEmpty && out.isEmpty

  def push(elem: A): Queue[A] = new Queue(elem :: in, out)


  def pop(): (A, Queue[A]) =
    out match {
      case head :: tail => {
        println("out match")
        (head, new Queue(in, tail))
      }
      case Nil          => {
        println("nil match takes in")
        val head :: tail = in.reverse
        (head, new Queue(Nil, tail))

      } // throws exception if empty
    }

  def print = {
    val l =  out ++ in.reverse
    l.toString()
  }
  def front: A =
    if (out.nonEmpty) out.head
    else if (in.nonEmpty) in.last
    else throw new NoSuchElementException("front on empty queue")

  def rear: A =
    if (in.nonEmpty) in.head
    else if (out.nonEmpty) out.last
    else throw new NoSuchElementException("rear on empty queue")

  def length = in.length + out.length

   def tail: Queue[A] =
    if (out.nonEmpty) new Queue(in, out.tail)
    else if (in.nonEmpty) new Queue(Nil, in.reverse.tail)
    else throw new NoSuchElementException("tail on empty queue")

}

var q = Queue.empty[Int]
(1 to 10).foreach(i => q = q.push(i))
q.front
val pr = q.pop()
val x2 = pr._2.push(11)
val x3 = x2.push(12)
x3.print

val x4 = x3.pop()
val x5 = x4._2.pop()
x5._2.print
val x6 = x5._2
x6.front

x6.length
val x7 = x6.tail
x7.print

x7.rear
x7.front*/




