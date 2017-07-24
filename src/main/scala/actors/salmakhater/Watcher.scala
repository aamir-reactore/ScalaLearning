package actors.salmakhater

import akka.actor.{ActorIdentity, ActorSystem, Identify, Props}

object WatcherTest extends App {
  import akka.actor.Actor

  /**
    * By having only actor name (counter here) we can retrieve actorRef from it
    */
  class Watcher extends Actor {
    val selection = context.actorSelection("/user/counter")
    selection ! Identify(None)

    override def receive = {
      case ActorIdentity(_,Some(ref)) =>
        println(s"Actor Reference for counter is ...$ref")
      case ActorIdentity(_,None) =>
        println(s"Actor Reference for actor doesn't live")
    }
  }
  class Counter extends Actor {
    import Counter._
    var count = 0
    override def receive = {
      case Inc(x) => count +=x
      case Dec(x) => count -=x
    }
  }

  val system = ActorSystem("watch-actor-selection")
  val counter = system.actorOf(Props[Counter],"counter")
  val watcher = system.actorOf(Props[Watcher],"watcher")

  Thread.sleep(100)
  system.terminate()
}
object Counter {
  final case class Inc(count:Int)
  final case class Dec(count:Int)
}