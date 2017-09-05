package actors.testssupervisor


import akka.actor.{Actor, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


class Test extends TestKit(ActorSystem()) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Echo actor" must {
    "send back messages unchanged" in {
      val echo = system.actorOf(TestActors.echoActorProps)
      echo ! "hello world"
      expectMsg("hello world")
    }

  }
}

class EchoActor extends Actor {
  override def receive = {
    case message => {
      println(s"message is $message")
      sender() ! message
    }
  }
}

object l extends App {
  val props: Props = Props[EchoActor]
  val actor = ActorSystem("test").actorOf(props)
   actor.tell("test",null)
}