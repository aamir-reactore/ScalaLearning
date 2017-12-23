package actors.erotcaer.actor

import akka.actor._
import akka.util.Timeout
import scala.concurrent.duration._
//import scala.reflect.ClassTag

/**
 */
trait ActorHelper {

  /**
  Creates ActorRef using ActorSystem
   */
  def getActorRef[T](clazz: Class[T]): ActorRef = {
    ActorSystemContainer.system.actorOf(Props.create(clazz))
  }

  def getActorRef[TActor](context: ActorContext, clazz: Class[TActor],name:String = null): ActorRef = {

    if(name == null) return context.actorOf(Props.create(clazz))

    val child = context.child(name)
        if(child.isDefined) return child.get
    context.actorOf(Props.create(clazz),name)
  }

}

object ActorHelper {
  val actorCreationTimeout = Timeout(5.seconds)
  val askTimeout = Timeout(12.seconds)
  val commonOperationsTimeout = Timeout(12.seconds)
  val requestTimeout = Timeout(12.seconds)
}

