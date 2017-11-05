
package actors.chatroom

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props, Terminated}

/**
  * Source:http://www.deadcoderising.com/2015-05-26-akka-change-an-actors-behavior-using-context-become/
  */
abstract class Msg
case class Info(msg:String) extends Msg
case class NewMessage(from:String,msg:String) extends Msg
case class Send(msg:String) extends Msg
case class Connect(username:String) extends Msg
case class BroadCast(msg:String) extends Msg
case class Disconnect(msg:String) extends Msg
case object Close
case object Open

class Server extends Actor {
  override def receive = open(List[(String,ActorRef)]())
  def open(clients:List[(String,ActorRef)]):Receive = {
    case Connect(username) => {
      broadcast(Info(s"$username has joined the chat"),clients)
      //context.watch(sender)
      context.become(open((username,sender) :: clients))
    }
    case BroadCast(message) => {
      val clientOption = clients.find(_._2 == sender)
      if(clientOption.isDefined) {
        val username = clientOption.get._1
        broadcast(NewMessage(username,message),clients)
      }
    }
    case Terminated(client) => {
      val clientOption = clients.find(_._2 == sender)
      val otherClients = clients.filterNot(_._2 == client)
      if(clientOption.isDefined) {
        val username = clientOption.get._1
        broadcast(Info(s"$username has left chat"),otherClients)
      }
    }
    case Close => {
      broadcast(Disconnect("chat box closed"),clients)
      //context.become(close)
    }
  }

  def close:Receive = {
    case Open   => context.become(open(List[(String,ActorRef)]()))
    case _      => sender ! Disconnect("chat closed")
  }
  def broadcast(info:Msg,clients:List[(String,ActorRef)]) = {
    clients.foreach(_._2 ! info)
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

object BroadCastChat extends App {

  val system = ActorSystem("Broadcaster")

  val server = system.actorOf(Props[Server],"Server")

  val client1 = system.actorOf(Props(new Client("aamir",server)),"Client1")
  Thread.sleep(100)
  val client2 = system.actorOf(Props(new Client("oby",server)),"Client2")

  Thread.sleep(300)

  client2 ! Send("Hi all")

  Thread.sleep(300)
  val client3 = system.actorOf(Props(new Client("faik",server)),"Client3")
  Thread.sleep(300)

  val client4 = system.actorOf(Props(new Client("rafaan",server)),"Client4")

  Thread.sleep(1000)

  client1 ! Send("lechmovo sarneee")
  Thread.sleep(1000)

 // client4 ! Disconnect("leaving")
  Thread.sleep(1000)

  server ! Close
}
