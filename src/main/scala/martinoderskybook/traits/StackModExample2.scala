package martinoderskybook.traits

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get():Int
  def put(x:Int)
}

class BasicIntQueue extends IntQueue {
    val buf = new ArrayBuffer[Int]()
  override def get: Int = buf.remove(0)

  override def put(x: Int): Unit = buf += x
}

trait Doubling extends IntQueue {
  abstract override def put(x:Int): Unit = {
    super.put(2 * x)
  }
}
/*class GG extends Doubling {
  //CTE, class GG needs to be a mixin, since method put in trait Doubling of type (x: Int)Unit is marked
  // `abstract' and `override', but no concrete implementation could be found in a base class
  class GG extends Doubling {
  override def get(): Int = 11
}
*/
/*
class ImpleDoubling extends Doubling {
  override def get(): Int = 10
  //CTE, `abstract override' modifier only allowed for members of traits
  abstract override def put(x:Int) = println()
}
*/
/**
  * So, this Doubling trait having abstract override method needs to be mixing with a class that also extends IntQueue
  */


class MyQueue extends BasicIntQueue with Doubling

/***
  * To see how to stack modifications, we need to define the other two modification traits, Incrementing and Filtering
  */
trait Incrementing extends IntQueue {
  abstract override def put(x:Int):Unit = {
    super.put(x + 1)
  }
}
trait Filtering extends IntQueue {
  abstract override def put(x:Int):Unit = {
    if(x >= 0) super.put(x)
  }
}

class  SMQueue extends BasicIntQueue with Incrementing with Filtering

object Queue1 extends App {

  val myQueue = new MyQueue
  myQueue.put(10)
  println(myQueue.get())
  println("####################")
  val smQ1 = new SMQueue
  smQ1.put(-1) // Incrementing and BasicIntQueue not calling as if fails
  smQ1.put(0)
  smQ1.put(1)
  //smQ1.buf.foreach(println)
  println(smQ1.get)
  println(smQ1.get)


}
//Now changing ordering of mixings
class  SMQueue1 extends BasicIntQueue with Filtering with Incrementing

object Queue2 extends App {
  val obj = new SMQueue1
  obj.put(-1)
  obj.put(0)
  obj.put(1)
  obj.buf.foreach(println)
}

/**

   So,
    Traits are a way to inherit from multiple class-like constructs, but they differ
    in important ways from the multiple inheritance present in many languages.
    One difference is especially important: the interpretation of super.
    With multiple inheritance, the method called by a super call can be determined
    right where the call appears. With traits, the method called is determined
    by a linearization of the classes and traits that are mixed into a class. This
    is the difference that enables the stacking of modifications

  **/
