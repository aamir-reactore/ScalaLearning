package marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/*object AsKpattern1 extends App {

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

object AsKpattern2 extends App {

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
  val askResponse: Future[NameResponse] = (actor ? AskName).mapTo[NameResponse]

  askResponse.foreach(x => println(s"Name is ${x.name}"))
}

object AsKpattern3 extends App {

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
  implicit val timeOut = Timeout(2.seconds)
  val askResponse: Future[NameResponse] = (actor1 ? AskName).mapTo[NameResponse]

  askResponse.foreach(x => println(s"Name is ${x.name}"))

  actor1 ! AskNameOf(actor2)
}*/

object AsKpattern4 extends App {

  case object AskName

  case class NameResponse(name: String)

  case class AskNameOf(actorRef: ActorRef)

  class AskActor(val name: String) extends Actor {
    //we can define ExecutionContext for the current system
    implicit val ec = context.system.dispatcher
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

  implicit val ec = system.dispatcher
  implicit val timeOut = Timeout(2.seconds)
  val askResponse: Future[NameResponse] = (actor1 ? AskName).mapTo[NameResponse]

  askResponse.foreach(x => println(s"Name is ${x.name}"))

  actor1 ! AskNameOf(actor2)
}