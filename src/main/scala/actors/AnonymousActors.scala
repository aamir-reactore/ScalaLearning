package actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object AnonymousActors1 extends App {

   val system = ActorSystem("Anonymous")
   val actorRef = system.actorOf(Props(new Actor {
     override def receive = {
       case s: String => println(s)
     }
   }))

  actorRef.tell("hello",actorRef)
  actorRef.tell("hello",null)
  actorRef.tell("hello",ActorRef.noSender)
  actorRef.tell("hello",Actor.noSender)
}