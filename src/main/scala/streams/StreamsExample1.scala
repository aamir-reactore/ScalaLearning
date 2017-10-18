package streams

import akka.NotUsed
import akka.actor._
import akka.stream._
import akka.stream.scaladsl.{RunnableGraph, Sink, Source}

import scala.concurrent.Future

/**
  * Following: https://stackoverflow.com/questions/35120082/how-to-get-started-with-akka-streams
  */
object Source1 extends App {

  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()

  val s1 = Source.empty
  println(s"s1 is $s1")
  val s2 = Source.single("single element")
  println(s"s2 is $s2")
  val s3 = Source(1 to 3)
  println(s"s3 is $s3")
  val s4 = Source.repeat(5)
  println(s"s4 is $s4")
  println("lazily evaluating s3====>")
  s2 runForeach println
  s3 runForeach println
  s4 take 3 runForeach println
}

object Source2 extends App {
  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()

 import scala.concurrent.ExecutionContext.Implicits.global
  def run(actor: ActorRef) = {
    Future { Thread.sleep(300); actor ! 1 }
    Future { Thread.sleep(200); actor ! 2 }
    Future { Thread.sleep(100); actor ! 3 }
  }
  val s: Source[Int, Future[Unit]] = Source.actorRef[Int](bufferSize = 0, OverflowStrategy.fail).mapMaterializedValue(run)
  s runForeach println
}

object Sink1 extends App {
  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()
  val source = Source(1 to 3)

  val sink = Sink.foreach[Int](elem => println(s"sink received: $elem"))

  val flow = source to sink

  flow.run()
}

object Sink2 extends App {
  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()

  val actor: ActorRef = system.actorOf(Props(new Actor {
    override def receive = {
      case msg => println(s"actor received: $msg")
    }
  }))
 /**
  * forward all values that arrive at a sink to an actor
  */
  val sink = Sink.actorRef[Int](actor,onCompleteMessage = "stream completed")
  val runnable= Source(1 to 3) to sink

  runnable.run()
}