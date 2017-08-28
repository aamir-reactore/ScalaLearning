package streams

import akka.stream.scaladsl.Source

object Streams1 extends App {
  val s = Source.empty
  println(s)
  import akka.actor._
  import akka.stream._
  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()
  import akka.stream.scaladsl.Source
  val s1 = Source.empty
  val s2 = Source.single("single element")
  val s3 = Source(1 to 3)
  s2 runForeach println
}