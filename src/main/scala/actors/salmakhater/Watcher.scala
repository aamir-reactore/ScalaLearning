package actors.salmakhater

import akka.actor.{ActorIdentity, ActorSystem, Identify, Props}

case object GetCount

object WatcherTest extends App {

  import akka.actor.Actor

  /**
    * By having only actor name (counter here) we can retrieve actorRef from it
    */
  /**
    * Identify(msgId)
    * A message all Actors will understand, that when processed will reply with
    * [[akka.actor.ActorIdentity]] containing the `ActorRef`. The `messageId`
    * is returned in the `ActorIdentity` message as `correlationId`.
    */
  class Watcher extends Actor {
    val selection = context.actorSelection("/user/counter")
    selection ! Identify("12")

    override def receive = {
      case ActorIdentity(correlationId, Some(ref)) =>
        println(s"Actor Reference for counter is ...$ref and actor name itself is ${ref.path.name}, correlationId = $correlationId")
      case ActorIdentity(_, None) =>
        println(s"Actor Reference for actor doesn't live")
    }
  }

  class Counter extends Actor {

    import Counter._
    var count = 0

    override def receive = {
      case Inc(x) => count += x
      case Dec(x) => count -= x
      case GetCount => sender ! count
    }
  }

  val system = ActorSystem("watch-actor-selection")
  //val counter = system.actorOf(Props[Counter], "counter")
  val watcher = system.actorOf(Props[Watcher], "watcher")

  Thread.sleep(100)
  system.terminate()
}

object Counter {
  final case class Inc(count: Int)
  final case class Dec(count: Int)
}