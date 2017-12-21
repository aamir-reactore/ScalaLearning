package actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object AnonymousActors1 extends App {

   val system = ActorSystem("Anonymous")
   val actor = system.actorOf(Props(new Actor {
     override def receive = {
       case s: String => println(s)
     }
   }))
  actor.tell("hello",actor)
  actor.tell("hello",null)
  actor.tell("hello",ActorRef.noSender)
  actor.tell("hello",Actor.noSender)
}