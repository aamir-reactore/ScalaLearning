
import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.ActorRefFactory

class Parent extends Actor {
  val child = context.actorOf(Props[Child], "child")
  var ponged = false

  def receive = {
    case 'pingit => child ! 'ping
    case 'pong => ponged = true
  }
}

class Child extends Actor {
  def receive = {
    case 'ping => context.parent ! 'pong
  }
}

class DependentChild(parent: ActorRef) extends Actor {
  def receive = {
    case 'ping => parent ! 'pong
  }
}

class DependentParent(childProps: Props) extends Actor {
  val child = context.actorOf(childProps, "child")
  var ponged = false

  def receive = {
    case 'pingit => child ! 'ping
    case 'pong => ponged = true
  }
}

class GenericDependentParent(childMaker: ActorRefFactory => ActorRef) extends Actor {
  val child = childMaker(context)
  var ponged = false

  def receive = {
    case 'pingit => child ! 'ping
    case 'pong => ponged = true
  }
}