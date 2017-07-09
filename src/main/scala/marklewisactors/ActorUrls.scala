package marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorUrls1 extends App {

  case object CreateChild
  case class SignalChildren(order:Int)
  case class PrintSignal(order:Int)

  class ParentActor extends Actor {
    private var num = 0

    override def receive = {
      case CreateChild =>
        context.actorOf(Props[ChildActor], s"child-$num")
        num += 1
      case SignalChildren(n) =>
        context.children.foreach(_ ! PrintSignal(n))
    }
  }

  class ChildActor extends Actor {
    override def receive = {
      case PrintSignal(n) => println(s"$n--$self")
    }
  }

  val system = ActorSystem("ActorHierarchy1")
  val actor = system.actorOf(Props[ParentActor], "ParentActor1")

  actor ! CreateChild
  actor ! SignalChildren(1)
  actor ! CreateChild
  actor ! CreateChild
  actor ! CreateChild
  actor ! SignalChildren(2)

}