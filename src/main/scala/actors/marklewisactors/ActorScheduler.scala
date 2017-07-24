package actors.marklewisactors

import akka.actor.{Actor, ActorRef, ActorSystem, Cancellable, Props}

import scala.concurrent.duration._

object ActorSchedulerExample1 extends App {
  case object Count
  class SchedulerActor extends Actor {
    var num = 1
    override def receive = {
      case Count =>
        println(num)
        num +=1
    }
    def foo: Unit = println("Normal Method")
  }

  val system: ActorSystem = ActorSystem("SchedulerActorExample1")
  val actor: ActorRef = system.actorOf(Props[SchedulerActor], "SchedulerActor")
  implicit val ex = system.dispatcher
  system.scheduler.scheduleOnce(4.seconds)(actor ! Count)
  val cancel: Cancellable = system.scheduler.schedule(5.seconds,1.seconds,actor,Count)

  Thread.sleep(10000)
  cancel.cancel()
  //system.terminate()
}

