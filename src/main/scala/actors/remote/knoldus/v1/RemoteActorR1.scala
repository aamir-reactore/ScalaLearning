package actors.remote.knoldus.v1

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class RemoteActorR1 extends Actor with ActorLogging {
  override def receive = {
    case msg => log.info(s"Server received message : $msg")
  }
}
object RemoteActorR1 extends App {
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
      |      port = 2551
      |    }
      |  }
      |}
    """.stripMargin

  val config = ConfigFactory.parseString(conf)
  val server = ActorSystem("remote-r1",config)
  server.actorOf(Props[RemoteActorR1],"RemoteActorR1")
}