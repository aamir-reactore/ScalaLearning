package actors.remote.knoldus.v1

import akka.actor.{Actor, ActorLogging}

class RemoteActorR2 extends Actor with ActorLogging {
  override def receive: Receive = ???
}

object RemoteActorR2 {
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

}