package marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorHierarchy1 extends App {

  case object CreateChild
  case object SignalChildren
  case object PrintSignal
  class ParentActor extends Actor {
    private var num = 0
    private val children = collection.mutable.Buffer[ActorRef]()
    override def receive = {
      case CreateChild =>
        children += context.actorOf(Props[ChildActor],s"child-$num")
        num += 1
      case SignalChildren =>
        children.foreach(_ ! PrintSignal)
    }
  }

  class ChildActor extends Actor {
    override def receive = {
      case PrintSignal => println(self)
    }
  }

  val system = ActorSystem("ActorHierarchy1")
  val actor = system.actorOf(Props[ParentActor], "ParentActor1")

  actor ! CreateChild
  actor ! SignalChildren
  actor ! CreateChild
  actor ! CreateChild
  actor ! CreateChild
  actor ! SignalChildren

}