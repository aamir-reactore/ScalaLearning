package actors.erotcaer.actor

/**
  */

import akka.actor._
import akka.pattern.ask
import akka.routing.RoundRobinPool
import org.joda.time.DateTime

import scala.concurrent.Await
import scala.reflect._

trait CoreActorSystem {
  implicit def system: ActorSystem
}

/**
  */
trait BootedCoreActorSystem extends CoreActorSystem {

  implicit lazy val system = ActorSystemContainer.system

  sys.addShutdownHook(system.terminate())
}

class RootSupervisor extends BaseActor {

  // Create a new actor under op supervisor
  receiver {
    case cmd:CreateActorRefCommand => {
      if(cmd.name.isDefined)
      sender ! context.actorOf(cmd.props, cmd.name.get)
      else sender ! context.actorOf(cmd.props)
    }
   // case props: Props => sender ! context.actorOf(props)
  }
}

object SupervisorContainer extends BootedCoreActorSystem {
  lazy val rootSupervisor = system.actorOf(Props[RootSupervisor])
}

trait RootSupervisorHelper {
  self: CoreActorSystem =>
 lazy val rootSupervisor = SupervisorContainer.rootSupervisor
}

trait SuperActors {
  self: RootSupervisorHelper =>

  val availableProcessors = 1 //Runtime.getRuntime.availableProcessors

  def actorOf[T <:Actor : ClassTag](actorProperties: Props, name: Option[String] = None): ActorRef = {
    val finalName =  name.getOrElse(actorProperties.clazz.getSimpleName)
    if(finalName.toLowerCase().equals("none")) throw new IllegalArgumentException(
      """
        |
        |
        | Provide either name or type of actor for %-12s
        |
        |
      """.stripMargin.format(actorProperties))

    val actorRefFuture = ask(rootSupervisor, CreateActorRefCommand(actorProperties,Some(finalName)))(ActorHelper.actorCreationTimeout).mapTo[ActorRef]
    Await.result(actorRefFuture, ActorHelper.actorCreationTimeout.duration)
  }

  @deprecated("Name changed. Use actorOf instead","0.8.5.7-Kamon-snapshot")
  def getActorRef[T <:Actor : ClassTag](actorProperties: Props, name: Option[String] = None): ActorRef = {
    actorOf[T](actorProperties,name)
  }

  def actorOf[T <:Actor : ClassTag](clazz: Class[T]): ActorRef = {
   // val finalName = classTag[T].runtimeClass.getName
    val props = Props(clazz)
    val actorRefFuture = ask(rootSupervisor, CreateActorRefCommand(props,Some(props.clazz.getSimpleName)))(ActorHelper.actorCreationTimeout).mapTo[ActorRef]
    Await.result(actorRefFuture, ActorHelper.actorCreationTimeout.duration)
  }

  @deprecated("Named changed. Use actorOf instead","0.8.5.7-Kamon-snapshot")
  def getActorRef[T <:Actor : ClassTag](clazz: Class[T]): ActorRef = {
    actorOf[T](clazz)
  }

  def createRouters[T <:Actor : ClassTag](actorProperties: Props, nrOfInstances: Int = availableProcessors, name: Option[String] = None) = {
    actorOf[T](RoundRobinPool(nrOfInstances).props(actorProperties),name)
  }

  def createRoutersByType[T <:Actor : ClassTag](nrOfInstances: Int = availableProcessors) = {
    actorOf[T](RoundRobinPool(nrOfInstances).props(Props(classTag[T].runtimeClass)))
  }
}
trait CreateDate {
  val createDate: DateTime
}

trait MineEvent extends CreateDate {
  override val createDate: DateTime = DateTime.now
  val mineId: Long
}
object ActorSystemContainer  {
  lazy val system: ActorSystem =  ActorSystem("SuperActorSystem")

  def publish[E <: MineEvent](event:E) = {
    system.eventStream.publish(event)
  }

  def subscribe[E <: MineEvent](actorRef: ActorRef, channel:Class[E]) = {
    system.eventStream.subscribe(actorRef, channel)
  }
}

private[actor] case class CreateActorRefCommand(props: Props, name: Option[String] = None)


trait DefaultSuperActorHelper extends SuperActors with RootSupervisorHelper with BootedCoreActorSystem
//////////////////////Test
/*
class Actor1 extends Actor {
  override def receive: Receive ={
    case msg =>
  }
}


object ProcessingActors extends SuperActors with BootedCoreActorSystem with RootSupervisorHelper with App {
 val actoreRef = getActorRef(classOf[Actor1])
}*/

