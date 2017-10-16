package actors.chatroom

import actors._
import akka.actor.{Actor, ActorRef, PoisonPill}
abstract class Msg
case class Info(msg:String) extends Msg
case class NewMessage(from:String,msg:String) extends Msg
case class Send(msg:String) extends Msg
case class Connect(username:String) extends Msg
case class BroadCast(msg:String) extends Msg
case class Disconnect(msg:String)

class Server extends Actor {
  override def receive = open
  def open(clients:List[]):Receive ={

  }
}
class Client(username:String,server:ActorRef) extends Actor {
  server ! Connect(username)
  override def receive = {
    case info:Info => {
      println(s"[$username's client]- ${info.msg}")
    }
    case send: Send => {
      server ! BroadCast(send.msg)
    }
    case newMsg:NewMessage => {
      println(s"[$username's client]- from = ${newMsg.from},message = ${newMsg.msg}")
    }
    case Disconnect(msg) => {
      println(s"[$username's client]- $msg")
      self ! PoisonPill
    }
  }
}