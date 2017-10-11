package actors.chatroom

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}

abstract class Msg
case class Send(msg:String) extends Msg
case class NewMsg(from:String,msg:String) extends Msg
case class Info(msg:String) extends Msg
case class Connect(username:String) extends Msg
case class Broadcast(msg:String) extends Msg
case object Disconnect extends Msg

class Server extends Actor {
  var clients = List[(String,ActorRef)]()
  def receive  = {
    case Connect(username) => {
      broadcast(Info(s"$username joined the chat"))
      clients = (username,sender) :: clients
    }
    case Broadcast(msg) => {
      val username = clients.filter(_._2 == sender).head._1
      broadcast(NewMsg(username,msg))
    }
  }
  def broadcast(msg:Msg) = {
    clients.foreach(_._2 ! msg)
  }
}

class Client(val username:String,server:ActorRef) extends Actor {
    server ! Connect(username)
    def receive = {
      case NewMsg(from, msg) => {
        println(s"[$username's client] - from = $from, message = $msg")
      }
      case Send(msg) => server ! Broadcast(msg)
      case Info(msg) => {
        println(s"[$username's client] - $msg")
      }
      case Disconnect => {
        println(s"[$username's client] - disconnected")
        self ! PoisonPill
      }
   }
}

object ChatBox extends App {
  val system = ActorSystem("System")
  val server = system.actorOf(Props[Server])

  val c1 = system.actorOf(Props(new Client("Sam",server)))
  c1 ! Send("Hi, anyone here?")

  Thread.sleep(1000)
  val c2 = system.actorOf(Props(new Client("Mia", server)))
  Thread.sleep(1000)

 val c3 = system.actorOf(Props(new Client("Luke", server)))


}