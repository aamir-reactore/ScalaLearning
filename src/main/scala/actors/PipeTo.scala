package actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
case class RespondToActorB(message:Any)
object PipeToExample1 extends App {

  class ActorA extends Actor {
    def receive = {
      case message: String =>
        println("inside ActorA message block")
        val actorB = context.actorOf(Props[ActorB], "ActorB")
        actorB ! message
      case x: RespondToActorB =>
        println("Response when ActorB processed ActorC response and piped that to me")
    }
  }

  class ActorB extends Actor {
    def receive = {
      case _: String =>
        println("inside ActorB message block")
        val actorC = context.actorOf(Props[ActorC], "ActorC")
        implicit val to = Timeout(2.seconds)
         ask(actorC,"asking actorc").mapTo[RespondToActorB] pipeTo sender
    }
  }

  class ActorC extends Actor {
    def receive = {
      case message: String =>
        println("inside ActorC message Block")
        sender ! RespondToActorB(None)
    }
  }

  val system = ActorSystem("PipeToExample1")
  val actor = system.actorOf(Props[ActorA], "ActorA")
  actor.tell("some message",ActorRef.noSender)
}