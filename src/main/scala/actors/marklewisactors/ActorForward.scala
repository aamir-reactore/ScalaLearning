package actors.marklewisactors

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
case object FromActor3
/**
  * forward method: Forwards the message and passes the original sender actor as the sender
  */
object ActorForward extends App {

  class ActorExample extends Actor {
    def receive = {
      case message: String =>
        println(s"Message received from ${sender.path.name}, message = $message")
        val child = context.actorOf(Props[Actor2], "ChildActor")
        child ! message
      case FromActor3 =>
        println("Response when forwarded by Actor2 to ActorL to Actor3")
        //context.stop(self) // to terminate child actor
        self ! PoisonPill // will recursively stop all its child actors.
    }
    override def postStop() {
      println("Child ActorExample stopped")
    }
  }

  class Actor2 extends Actor {
    def receive = {
      case message: String =>
        println(s"Message received from ${sender.path}, message = $message")
        val child = context.actorOf(Props[ActorL], "ChildActor")
        println("forwarding...")
        child forward message
      case FromActor3 => println("should not hit this..Actor2.")
      case _ => println("Unknown message")
    }
    override def postStop() {
      println("Child Actor2 stopped")
    }
  }

  class ActorL extends Actor {
    def receive = {
      case message: String =>
        println(s"Message received from ${sender.path}, message = $message")
        val child = context.actorOf(Props[Actor3], "ChildActor")
        println("forwarding...")
        child forward message
      case FromActor3 => println("should not hit this..ActorL.")
      case _ => println("Unknown message")
    }
    override def postStop() {
      println("Child ActorL stopped")
    }
  }
  class Actor3 extends Actor {
    def receive = {
      case message: String =>
        println(s"Message received from ${sender.path}, message = $message")
        sender ! FromActor3
      case _ => println("Unknown message")
    }
    override def postStop(){
      println("Child Actor3 stopped")
    }
  }


  val actorSystem = ActorSystem("ActorSystem")
  val actor = actorSystem.actorOf(Props[ActorExample], "RootActor")
  actor ! "Hello"
  Thread.sleep(1000)
 // actor ! "Hello" // throws error as actor was stopped.
  //actorSystem.terminate() to stop actorSystem
}