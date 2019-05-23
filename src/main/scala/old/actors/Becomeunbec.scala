package old.actors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Source :==> https://alvinalexander.com/scala/akka-old.actors-switch-between-states-with-become
  */
case object ActNormalMessage
case object TryToFindSolution
case object BadGuysMakeMeAngry

class EdNorton extends Actor {

  def angryState:Receive = {
    case ActNormalMessage => {
      println("Phew, I'm back to being Norton")
      context.become(normalState)
    }
  }
  def normalState:PartialFunction[Any, Unit] ={
    case TryToFindSolution => {
      println("Looking for solution to my problem...")
    }
    case BadGuysMakeMeAngry => {
      println("I'm getting angry.....")
      context.become(angryState)
    }
  }
  override def receive = {
    case BadGuysMakeMeAngry => context.become(angryState)
    case  ActNormalMessage  => context.become(normalState)
  }
}
object Becomeunbec extends App {

  val system = ActorSystem("BecomeHulkExample")

  val ed = system.actorOf(Props[EdNorton], name = "EdNorton")

  ed ! ActNormalMessage // init to normalState
  ed ! TryToFindSolution
  ed ! BadGuysMakeMeAngry

  Thread.sleep(1000)
  ed ! ActNormalMessage
  ed ! TryToFindSolution


}