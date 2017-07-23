package streams

import akka.stream.scaladsl.Source

object Streams1 extends App {
  val s = Source.empty
  println(s)
}