package actors.marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorReplyingTest extends App {

  class ActorReplyExample extends Actor {
    var num = 0
    def receive = {
      case message: String =>
         println(s"Message received from ${sender.path.name} and $self, message = $message")
        val child = context.actorOf(Props[ActorChildReplyExample], s"ActorChild-$num")
        Thread.sleep(1000)
         num += 1
        child ! "Hello Child"
    }
  }


  class ActorChildReplyExample extends Actor {
    def receive = {
      case message: String =>
        println(s"Message received from ${sender.path.name} and $self, message = $message")
        println(s"Replying to ${sender.path.name}")
        sender ! "I got you message"
    }
  }

  val actorSystem = ActorSystem("ActorSystem")
  val actor: ActorRef = actorSystem.actorOf(Props[ActorReplyExample], "RootActor")
  println(actor.path)
  println(actor)
  actor ! "Hello"

}
