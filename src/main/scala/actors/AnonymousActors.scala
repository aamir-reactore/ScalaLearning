package actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object AnonymousActors1 extends App {

   val system = ActorSystem("Anonymous")
   val actorRef = system.actorOf(Props(new Actor {
     override def receive = {
       case s: String => println(s)
     }
   }))
<<<<<<< HEAD
  actor.tell("hello",actor)
  actor.tell("hello",null)
  actor.tell("hello",ActorRef.noSender)
  actor.tell("hello",Actor.noSender)
=======
  actorRef.tell("hello",actorRef)
  actorRef.tell("hello",null)
  actorRef.tell("hello",ActorRef.noSender)
  actorRef.tell("hello",Actor.noSender)
>>>>>>> 39522ce0a1c82a678aa8466de16bb4af5a32234f
}