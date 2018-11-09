package actors.marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object ActorReplyingTest extends App {

  class ActorReplyExample extends Actor {
    var num = 0
    def receive = {
      case message: String =>
         println(s"Message received from ${sender.path.name} and $self, message = $message")
         println(s">>>>>>>>>>>>>>>>>>Message received from $context")
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
        sender ! "I got your message"
    }
  }

  val actorSystem = ActorSystem("ActorSystemName")
  val actor: ActorRef = actorSystem.actorOf(Props[ActorReplyExample], "RootActor")
  println(s"actor path ===> ${actor.path}")
  println(s"actor name ===> ${actor.path.name}")
  println(s"actor object ===> $actor")
  actor ! "Hello"

}



class ActorExample extends Actor {
  def receive = {
    case message:String => println("Message received: "+message+" from outside actor instance");
      println("Replaying");
        sender() ! "Hello, I got your message.";      // Replying message
  }
}

object ActorExample{
  def main(args:Array[String]){
    val actorSystem = ActorSystem("ActorSystem");
    val actor = actorSystem.actorOf(Props[ActorExample], "RootActor");
    implicit val timeout = Timeout(10 seconds);
    val future: Future[Any] = actor ? "Hello";
    val result:Any = Await.result(future, timeout.duration);
    println("Message received: "+result);
  }
}