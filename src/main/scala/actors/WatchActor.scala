
import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}

class Kenny extends Actor {
  override def receive = {
    case _ => println("Kenny received a message")
  }
}
class KennysDad extends Actor {

  val kenny = context.actorOf(Props[Kenny], name = "Kenny")
  context.watch(kenny)
  override def receive = {
    case Terminated(kny) => println(s"OMG, kenny's dad killed ${kny.path.name}")
    case _ => println("kenny's dad received a message")
  }
}

object KennyTrial extends App {
  val system = ActorSystem("KennysTrial")
  val kennyDad = system.actorOf(Props[KennysDad], name ="Kenny'sDad")
  val poorKenny = system.actorSelection("/user/Kenny'sDad/Kenny")

  poorKenny.tell("hey kenny whatz up!!!",Actor.noSender)
  poorKenny ! PoisonPill

  Thread.sleep(5000)
  println("calling system.terminate")
  system.terminate()
}