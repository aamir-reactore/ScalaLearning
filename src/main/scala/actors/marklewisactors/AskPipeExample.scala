import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import akka.pattern.pipe

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}

class ActorIncrement extends Actor {

  def receive = {
    case i: Int =>
      println(s"increment $i")
      sender ! i + 1
  }
}

class ActorEven extends Actor {

  def receive = {
    case i: Int =>
      println(s"$i is even")
  }
}


class ActorOdd extends Actor {

  def receive = {
    case i: Int =>
      println(s"$i is odd")
  }
}

class MyActor(actorIncrement: ActorRef, actorEven: ActorRef, actorOdd: ActorRef) extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val timeout = Timeout(5 seconds)

  def receive = {
    case i: Int if i % 2 == 0 =>
      println(s"receive a: $i")
      actorIncrement ? i map {
        case j: Int =>
          println(s"$j from increment a")
          actorOdd ! j
      }
    case i: Int =>
      println(s"receive b: $i")
      val future: Future[Any] = actorIncrement ? i
      val r: Unit = future onSuccess {
        case i: Int =>
          println(s"$i from increment b")
          actorEven ! i
      }

    case s: String =>
      import scala.concurrent._
      import scala.concurrent.Await
      println(s"receive c: $s")
      val r= (actorIncrement ? s.toInt).mapTo[Int] filter(_ % 2 == 0) andThen { case Success(i: Int) => println(s"$i from increment c");case Failure(_) => println("filter pridicate failed")} pipeTo actorEven
      val res = Await.result(r, Timeout(2.second).duration) // throws java.util.NoSuchElementException: Future.filter predicate is not satisfied, when s is even
      println(res)//, gives result when s is odd
  }
}

object TalkToActor extends App {

  // Create the 'talk-to-actor' actor system
  val system = ActorSystem("talk-to-actor")

  val actorIncrement = system.actorOf(Props[ActorIncrement], "actorIncrement")
  val actorEven = system.actorOf(Props[ActorEven], "actorEven")
  val actorOdd = system.actorOf(Props[ActorOdd], "actorOdd")

  val myActor = system.actorOf(Props(new MyActor(actorIncrement, actorEven, actorOdd)), "myActor")

  //myActor ! 2
  //myActor ! 7
  myActor ! "11"

  Thread.sleep(1000)

  //shutdown system
  system.terminate()
}