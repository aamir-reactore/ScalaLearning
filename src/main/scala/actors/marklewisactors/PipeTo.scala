package actors.marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.{ask, pipe}

import scala.concurrent.ExecutionContext.Implicits.global
case object RespondToActorB
object PipeToExample1 extends App {

  class ActorA extends Actor {
    def receive = {
      case message: String =>
        println("inside ActorA message block")
        val actorB = context.actorOf(Props[ActorB], "ActorB")
        actorB ! message
      case RespondToActorB =>
        println("Response when ActorB processed ActorC response and piped that to me")
    }
  }

  class ActorB extends Actor {
    def receive = {
      case message: String =>
        println("inside ActorB message block")
        val actorC = context.actorOf(Props[ActorC], "ActorC")
        val fromActorC = ask(actorC,"asking actorc").map { x=>
          println("asked actorc and got positive response")
          println(x)
        }.pipeTo(sender)
    }

  }

  class ActorC extends Actor {
    def receive = {
      case message: String =>
        println("inside ActorC message Block")
      sender ! RespondToActorB
    }
  }

  val system = ActorSystem("PipeToExample1")
  val actor = system.actorOf(Props[ActorA], "ActorA")
  actor.tell("some message",ActorRef.noSender)
}