package actors.erotcaer.actor


import akka.actor.SupervisorStrategy._
import akka.actor._

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._

//Read more: https://github.com/sksamuel/akka-patterns
trait BaseActor extends Actor {

  lazy val receivers = new ListBuffer[Actor.Receive]

  def receiver(pf: Actor.Receive): Unit = {
    receivers.append(pf)
  }

  final def receive = {
    case msg => {
      val matches = receivers.filter(pf => pf.isDefinedAt(msg))
      if (matches.size > 1) {
        println(
          """
            |+---------------------------------------------------------------------------------------+
            ||                                                                                       |
            ||                                                                                       |
            ||  Duplicate matches found for the sent message, check for possible _ match             |
            ||  or duplicate pattern matches %-15s
            ||  Found matches: %-12s
            ||                                                                                       |
            ||                                                                                       |
            ||+--------------------------------------------------------------------------------------+"""
            .stripMargin.format(msg, matches))
        val exception = DuplicateMatchesException(s" Duplicate matches found for the sent message $msg, found matches $matches ")
        sender ! akka.actor.Status.Failure(exception)
      }
      if (matches.nonEmpty) {
        matches.headOption.get(msg)
        interceptMsg(msg)
      }
      else {

        println(
          """
            |+-----------------------------------------------------------------------------------------------------------------------------------------+
            ||                                                                                                                                         |
            ||                                                                                                                                         |
            || No match found for the message %-12s  received at %-12s                                                                                 |
            ||                                                                                                                                         |
            ||                                                                                                                                         |
            ||+----------------------------------------------------------------------------------------------------------------------------------------+"""
            .stripMargin.format(msg, this.getClass.getName))
        val exception = NoMatchFoundException(s" No match found for the message $msg  received at ${this.getClass.getName}  ")
        sender ! akka.actor.Status.Failure(exception)
      }

    }
  }

  def interceptMsg(msg: Any) = {
    //do nothing here
  }

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 5.second) {
    case ex: ActorInitializationException => {
      println("Stopping actor, cause " + ex.getCause + ", message " + ex.printStackTrace())
      Stop
    }
    case ex: IllegalArgumentException => {
      println("Stopping actor, cause " + ex.getCause + ", message " + ex.printStackTrace())
      Escalate
    }
    case ex: Exception =>
      println("Recreating dead Super instance, cause " + ex.getCause + ", message " + ex.printStackTrace())
      Resume
  }

  def stopMe() {
    context.stop(self)
  }

  def tellSenderAndStop(message: AnyRef) {
    sender ! message
    stopMe()
  }
}

case class DuplicateMatchesException(msg: String) extends IllegalArgumentException(msg)

case class NoMatchFoundException(msg: String) extends IllegalArgumentException(msg)



