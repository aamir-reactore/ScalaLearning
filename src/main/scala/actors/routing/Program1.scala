package actors.routing

import DonutStoreProtocol.{CheckStock, WorkerFailedException}
import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor, ActorLogging, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
object DonutStoreProtocol {
  case class Info(name: String)

  case class CheckStock(name: String)

  case class WorkerFailedException(error: String) extends Exception(error)
}

class DonutStockWorkerActor extends Actor with ActorLogging {

  @throws[Exception](classOf[Exception])
  override def postRestart(reason: Throwable): Unit = {
    log.info(s"restarting ${self.path.name} because of $reason")
  }

  def receive = {
    case CheckStock(name) =>
      findStock(name)
      context.stop(self)
  }

  def findStock(name: String): Int = {
    log.info(s"Finding stock for donut = $name")
    100
     //throw new IllegalStateException("boom") // Will Escalate the exception up the hierarchy
    throw new WorkerFailedException("boom") // Will Restart DonutStockWorkerActor
  }
}

class DonutStockActor extends Actor with ActorLogging {

  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 3, withinTimeRange = 1 seconds) {
      case _: WorkerFailedException =>
        log.error("Worker failed exception, will restart.")
        Restart

      case _: Exception =>
        log.error("Worker failed, will need to escalate up the hierarchy")
        Escalate
    }

  val workerActor = context.actorOf(Props[DonutStockWorkerActor], name = "DonutStockWorkerActor")

  def receive = {
    case checkStock @ CheckStock(name) =>
      log.info(s"Checking stock for $name donut")
      workerActor forward checkStock
  }
}

object TestDonoutActor extends App {
  val system = ActorSystem("DonutStoreActorSystem")

  val donutStockActor = system.actorOf(Props[DonutStockActor], name = "DonutStockActor")

  implicit val timeout = Timeout(5 second)
  println("**********************************")
  val vanillaDonutStock: Future[Int] = (donutStockActor ? CheckStock("vanilla")).mapTo[Int]
  val result = for {
    found <- vanillaDonutStock
  } yield found
  result.map(res => println(s"Vanilla donut stock = $res"))

  Thread.sleep(5000)
  val isTerminated = system.terminate()


}