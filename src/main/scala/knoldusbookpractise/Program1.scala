package knoldusbookpractise

import scala.collection.immutable.Stream
import scala.collection.immutable.Stream.{Cons, cons}

object Program1 extends App {

   def sorted[A](as: Array[A], ordering: (A, A) => Boolean): Boolean = {
     def go(n: Int): Boolean = {
       if(n >= as.length - 1) true
       else if(ordering(as(n), as(n + 1))) false
       else go(n + 1)
     }
     go(0)
   }

  println( sorted(Array(1, 5, 11, 15), (x:Int, y: Int) => x > y) )
  println( sorted(Array(1, 5, 11, 15), (x:Int, y: Int) => x < y) )
  println( sorted(Array("Scala", "Programming"), (x:String, y: String) => x.length > y.length) )
}

object Program2 extends App {

  object Animal {
    def speak(something: String)(implicit nice: String) = {
      println(s"$something $nice")
    }
  }

  implicit val nice = "the wolf"

    Animal.speak("I am")

    Animal.speak("I like")("dog food")
}

object Program3 extends App {

   def from(n: Int): Stream[Int] = cons(n, from(n + 1))

   val res: Stream[Int] = from(100)

    println(res.take(40).toList)

  val res1: Seq[Int] = from(20)
  println(res1)

  val s: Stream[Int] = 1 #:: 2 #:: 3 #:: Stream.empty

}