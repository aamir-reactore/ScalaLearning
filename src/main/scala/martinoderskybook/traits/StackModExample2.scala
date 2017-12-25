package martinoderskybook.traits

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get():Int
  def put(x:Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]()
  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = buf += x
}

object Queue extends App {
  val queue = new BasicIntQueue
  queue.put(10)
  queue.put(20)
  println(queue.get())
}

