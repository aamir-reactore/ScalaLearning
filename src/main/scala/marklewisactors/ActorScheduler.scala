package marklewisactors

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
  system.terminate()
}

object ll extends App {
  trait A2 {  def string = "" }
  trait B2 extends A2 { override def string = "B String" + super.string }
  trait C2 extends B2 { override def string = "C String" + super.string }
  class MultipleMixinM2 extends B2 with C2
   println(new MultipleMixinM2().string)
}