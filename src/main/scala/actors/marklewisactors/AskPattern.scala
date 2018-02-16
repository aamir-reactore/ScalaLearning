
package actors.marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}
 /*
object AskPattern1 extends App {

  case object AskName
  case class NameResponse(name: String)

  class AskActor(val name: String) extends Actor {
    override def receive = {
      case AskName => sender ! NameResponse(name)
    }
  }

  val system = ActorSystem("AskPatternActorSystem")
  val actor = system.actorOf(Props(new AskActor("jimmyactor")), "AskActor1")
  implicit val timeOut = Timeout(2.seconds)
  val askResponse: Future[Any] = actor ? AskName

  askResponse.foreach(x => println(s"Name is $x"))
}
import akka.pattern.pipe

object AskPattern2 extends App {

  case object AskName
  case class  NameResponse(name: String)
  class AskActor(val name: String) extends Actor {
    override def receive = {
      case AskName => {
        sender ! NameResponse(name)
        //self ! PoisonPill
      }
      case NameResponse(naam) => {
        println(s"piped here $naam")
      }
    }
  }
  val system = ActorSystem("AskPatternActorSystem")
  val actor = system.actorOf(Props(new AskActor("jimmyactor")), "AskActor1")
  implicit val timeOut = Timeout(2.seconds)
  val askResponse  = (actor ? AskName).mapTo[NameResponse]

  askResponse.foreach(x => println(s"Name is ${x.name}"))

  val askResponsePiping1  = (actor ? AskName) pipeTo actor
  val askResponsePiping2  = askResponse pipeTo actor
}

object AskPattern3 extends App {

  case object AskName
  case class NameResponse(name: String)
  case class AskNameOf(actorRef: ActorRef)

  class AskActor(val name: String) extends Actor {
    override def receive = {
      case AskName => sender ! NameResponse(name)
      case AskNameOf(other) =>
        val res = other ? AskName
        res onComplete {
          case Success(NameResponse(s)) =>
            println(s"They said their name was $s")
          case Success(_) =>
            println("They didn't say their name")
          case Failure(_) =>
            println("Asking name failed")
        }
    }
  }

  val system = ActorSystem("AskPatternActorSystem")
  val actor1 = system.actorOf(Props(new AskActor("jimmyactor")), "AskActor1")
  val actor2 = system.actorOf(Props(new AskActor("markactor")), "AskActor2")
  implicit val timeOut = Timeout(2.seconds)
  val askResponse: Future[NameResponse] = (actor1 ? AskName).mapTo[NameResponse]

  askResponse.foreach(x => println(s"Name is ${x.name}"))

  actor1 ! AskNameOf(actor2)
}


object AskPattern4 extends App {

  case object AskName
  case class NameResponse(name: String)
  case class AskNameOf(actorRef: ActorRef)

  class AskActor(val name: String) extends Actor {
    //we can define ExecutionContext for the current system
    //implicit val ex = context.system.dispatcher

    override def receive = {
      case AskName => sender ! NameResponse(name)
      case AskNameOf(other) =>
        val res = other ? AskName
        res onComplete {
          case Success(NameResponse(s)) =>
            println(s"They said their name was $s")
          case Success(s) =>
            println("They didn't say their name")
          case Failure(f) =>
            println("Asking name failed")
        }
    }
  }

  val system = ActorSystem("AskPatternActorSystem")
  val actor1 = system.actorOf(Props(new AskActor("jimmyactor")), "AskActor1")
  val actor2 = system.actorOf(Props(new AskActor("markactor")), "AskActor2")

  implicit val ewwx = system.dispatcher
  implicit val timeOut = Timeout(2.seconds)
  val askResponse: Future[NameResponse] = (actor1 ? AskName).mapTo[NameResponse]

  askResponse.foreach(x => println(s"Name is ${x.name}"))

  actor1 ! AskNameOf(actor2)
}

object AskPattern5 extends App {

  class ActorExample extends Actor {
    def receive = {
      case message: String => println("Message received: " + message)
    }
  }

  //java.util.concurrent.TimeoutException as receive block doesn't respond with b.a reply
  val actorSystem = ActorSystem("ActorSystem")
  val actor = actorSystem.actorOf(Props[ActorExample], "RootActor")
  implicit val timeout: Timeout = Timeout(2.seconds)
  val future = actor ? "Hello"
  val result = Await.result(future, timeout.duration)
  println(result)

}
*/
object AskPattern6 extends App {

  class ActorExample extends Actor {
    def receive = {
      case message: String => {
        println("Message received: " + message)
        sender ! "some response"
      }
    }
  }

  val actorSystem = ActorSystem("ActorSystem")
  val actor = actorSystem.actorOf(Props[ActorExample], "RootActor")
  implicit val timeout = Timeout(2.seconds)
  val future = actor ? "Hello"
  val result = Await.result(future, timeout.duration)
  println(result)

}

object AskPattern7 extends App {

  class ActorExample extends Actor {
    def receive = {
      case message: String => {
        println("Message received: " + message)
        Thread.sleep(5000)
        sender ! "some response"
      }
    }
  }

  val actorSystem = ActorSystem("ActorSystem")
  val actor = actorSystem.actorOf(Props[ActorExample], "RootActor")
  implicit val timeout = Timeout(2.seconds)
  val future = actor ? "Hello"
  val result = Await.result(future, timeout.duration)
  println(result)

}
