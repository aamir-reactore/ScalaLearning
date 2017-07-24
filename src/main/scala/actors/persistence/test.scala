package actors.persistence

import akka.persistence._

case class Cmd(data: String)

case class Evt(data: String)

case class ExampleState(events: List[String] = Nil) {
  def updated(evt: Evt): ExampleState = copy(evt.data :: events)

  def size: Int = events.length

  override def toString: String = events.reverse.toString
}

class DemoPersistentActor extends PersistentActor {

  //note : This is  mutable
  var state = ExampleState()

  def updateState(event: Evt): Unit = state = state.updated(event)

  def numEvents = state.size

  val receiveRecover: Receive = {
    case evt: Evt => updateState(evt)
    case SnapshotOffer(_, snapshot: ExampleState) => {
      println("came here>>>>>>>>>>>>>>>>>>>>>>>>>")
      println(s"offered state = $snapshot")
      state = snapshot
    }
    case _ => println("came here 777777777777777777777")
  }

  val receiveCommand: Receive = {
    case Cmd(data) =>
      persist(Evt(s"$data-${numEvents + 1}")) { event =>
        updateState(event)
        context.system.eventStream.publish(event)
      }
    case "snap" => saveSnapshot(state)
    case SaveSnapshotSuccess(metadata) =>
      println(s"SaveSnapshotSuccess(metadata) :  metadata=$metadata")
    case SaveSnapshotFailure(metadata, reason) =>
      println(
        """SaveSnapshotFailure(metadata, reason) :
        metadata=$metadata, reason=$reason""")
    case "print" => println(">>>>>>" + state)
    case "boom" => throw new Exception("boom")
  }

  override def persistenceId = "demo-persistent-actor-1"
}

object pertest1 extends App {

  import akka.actor._

  import scala.language.postfixOps


  //create the actor system
  val system = ActorSystem("PersitenceSystem")

  val persistentActor = system.actorOf(Props(classOf[DemoPersistentActor]), "demo-persistent-actor-1")

  persistentActor ! "print"
  persistentActor ! Cmd("foo")
  persistentActor ! "snap"

  //  persistentActor ! Cmd("baz")
  //    persistentActor ! "boom"
  //    persistentActor ! Cmd("bar")
  //    persistentActor ! Cmd("buzz")
  //    persistentActor ! "print"


  Thread.sleep(1000)
  //shutdown the actor system
  system.terminate()

}