package actors.marklewisactors

import akka.actor.{Actor, ActorSystem, Props}


object SimpleActorExample1 extends App {

  class SimpleActor extends Actor {
    override def receive = {
      case s: String => println(s"String is $s")
      case n: Int => println(s"Number is $n")
    }

    def foo: Unit = println("Normal Method")
  }

  val system = ActorSystem("SimpleActorExample1")
  val actor = system.actorOf(Props[SimpleActor], "SimpleActor")

  println("Before Messages")
  actor ! "Hi there"
  println("After String")
  actor ! 10
  println("After Number")
  actor ! 'a'
  println("After Character")
}