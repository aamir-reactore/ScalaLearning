package actors.receivetimeouts

import akka.actor.{Actor, ActorRef, ActorSystem, Props, ReceiveTimeout}

import scala.concurrent.duration.Duration
import scala.concurrent.duration._

class ActorA extends Actor {
  val myB = context.actorOf(Props[ActorB])

  def receive = {
    case msg =>
      myB ! msg
      context.setReceiveTimeout(5.seconds)
      context.become(waitingForResponse)
  }

  def waitingForResponse:Receive = {
    case ReceiveTimeout =>
      println("got a receive timeout")
      cancelReceiveTimeout

    case response =>
      println("got my response back")
      cancelReceiveTimeout
  }

  def cancelReceiveTimeout = context.setReceiveTimeout(Duration.Undefined)
}
class ActorB extends Actor {
  def receive:Receive = {
    case _ => println("from ActorB")
      sender ! "some Response"
  }
}

object App1 extends App {
 // val system = ActorSystem("receive zcxzc") //  invalid ActorSystem name
  val system = ActorSystem("receive")
  val actorA = system.actorOf(Props[ActorA],"Actor-A")
  actorA.tell("msg",ActorRef.noSender)
}