package old.actors.remote.knoldus.v1

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class RemoteActorR2 extends Actor with ActorLogging {
  override def receive: Receive = ???
}

object RemoteActorR2 extends App {
  val conf =
    """
      |akka {
      |  actor {
      |    provider = "akka.remote.RemoteActorRefProvider"
      |  }
      |
      |  remote {
      |    enabled-transports = ["akka.remote.netty.tcp"]
      |    netty.tcp {
      |      hostname = "0.0.0.0"
      |      port = 2552
      |    }
      |  }
      |}
    """.stripMargin
  val config   = ConfigFactory.parseString(conf)
  val client   = ActorSystem("remote-r2", config)
  val path     = "akka.tcp://remote-r1@0.0.0.0:2551/user/RemoteActorR1"
  val server   = client.actorSelection(path)
  server ! "Hello Server"
}

