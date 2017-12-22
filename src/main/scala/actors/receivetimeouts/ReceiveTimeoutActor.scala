package actors.receivetimeouts

import java.util.Date

import akka.actor.{Actor, ActorSystem, Props, ReceiveTimeout}

import scala.concurrent.duration._

/**
  * Source ==>https://gist.github.com/tinoadams/2800556
  */
class ReceiveTimeoutActor1 extends Actor {
  context.setReceiveTimeout(5 seconds)
  def receive = {
    case x: String => println(new Date + " / Recieved: " + x)
    case ReceiveTimeout => println(new Date + " / No message received since 5 seconds")

  }
}

object Main1 extends App {
  val sys = ActorSystem("test")
  val props = Props[ReceiveTimeoutActor1]
  val a = sys.actorOf(props)
  println(new Date + " / Started")
  Thread.sleep(16000) // expect two timeout messages, one after 5 seconds, next after 10
  // send ten msg's (1 every second) just when we should receive the third timeout
  1 to 10 foreach { x =>
    a ! "Message: " + x
    Thread.sleep(1000)
  }
  // the next timeout message should be received 5 seconds after the loop finishes
}
class ReceiveTimeoutActor2 extends Actor {
  context.setReceiveTimeout(5 seconds)
  def receive = {
    case x: String => println(new Date + " / Recieved: " + x)
    case ReceiveTimeout => {
      println(new Date + " / No message received since 5 seconds")
      cancelReceiveTimeOut
    }
      def cancelReceiveTimeOut = context.setReceiveTimeout(Duration.Undefined)

  }
}
object Main2 extends App {
  val sys = ActorSystem("test")
  val props = Props[ReceiveTimeoutActor2]
  val a = sys.actorOf(props)
  println(new Date + " / Started")
  Thread.sleep(16000) // expect two timeout messages, one after 5 seconds, next after 10
  // send ten msg's (1 every second) just when we should receive the third timeout
  1 to 10 foreach { x =>
    a ! "Message: " + x
    Thread.sleep(1000)
  }
  // the next timeout message should be received 5 seconds after the loop finishes
}