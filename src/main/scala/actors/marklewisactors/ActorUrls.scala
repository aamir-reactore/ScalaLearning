package actors.marklewisactors

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}

object ActorUrls1 extends App {

  case object CreateChild
  case class SignalChildren(order: Int)
  case class PrintSignal(order: Int)

  class ParentActor extends Actor {
    private var num = 0

    override def receive = {
      case CreateChild =>
        context.actorOf(Props[ChildActor], s"child-$num")
        num += 1
      case SignalChildren(n) =>
      //  val goodLookup: Option[ActorRef] = context.child("kid")
       // println(goodLookup)
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
  actor ! CreateChild
  actor ! CreateChild

  //SignalChildren(1) will print out before SignalChildren(2)--thanks to actor inbox.
  actor ! SignalChildren(1)
  actor ! CreateChild
  actor ! CreateChild
  actor ! CreateChild
  actor ! SignalChildren(2)

}

object ActorUrls2 extends App {

  case object CreateChild
  case class SignalChildren(order: Int)
  case class PrintSignal(order: Int)

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

  val system   = ActorSystem("ActorHierarchy1")
  val actor    = system.actorOf(Props[ParentActor], "ParentActor1")
  val actor2   = system.actorOf(Props[ParentActor], "ParentActor2")

  actor2 ! CreateChild
  /**
    An ActorSelection is a logical view of a section of an ActorSystem's tree of Actors,
    allowing for broadcasting of messages to that section.
    **/
  val child0Actor2: ActorSelection = system.actorSelection("akka://ActorHierarchy1/user/ParentActor2/child-0")

  child0Actor2 ! PrintSignal(20)
         actor ! CreateChild

  actor ! SignalChildren(1)

}

/**
  * If ur working inside the same ActorSystem then we can leave off
  * protocol and actorSystem from actorSelection
  */
object ActorUrls3 extends App {

  case object CreateChild

  case class SignalChildren(order: Int)

  case class PrintSignal(order: Int)

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
  val actor2 = system.actorOf(Props[ParentActor], "ParentActor2")

  actor2 ! CreateChild
  val child0Actor2: ActorSelection = system.actorSelection("/user/ParentActor2/child-0")

  child0Actor2 ! PrintSignal(20)
  actor ! CreateChild

  actor ! SignalChildren(1)

}