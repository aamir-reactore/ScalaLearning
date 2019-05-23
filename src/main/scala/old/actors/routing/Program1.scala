package old.actors.routing

import DonutStoreProtocol.{CheckStock, WorkerFailedException}
import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.pattern.ask
import akka.routing.{DefaultResizer, RoundRobinPool}
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

  override def postRestart(reason: Throwable): Unit = {
    log.info(s"restarting ${self.path.name} because of $reason")
  }

  def receive = {
    case CheckStock(name) =>
      sender ! findStock(name)
  }

  def findStock(name: String): Int = {
    log.info(s"Finding stock for donut = $name, thread = ${Thread.currentThread().getId}")
    100
  }
}

class DonutStockActor extends Actor with ActorLogging {

  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 3, withinTimeRange = 5 seconds) {
      case _: WorkerFailedException =>
        log.error("Worker failed exception, will restart.")
        Restart

      case _: Exception =>
        log.error("Worker failed, will need to escalate up the hierarchy")
        Escalate
    }


  // We are using a resizable RoundRobinPool.
  val resizer: DefaultResizer = DefaultResizer(lowerBound = 5, upperBound = 10)
  val props: Props = RoundRobinPool(5, Some(resizer), supervisorStrategy = supervisorStrategy).props(Props[DonutStockWorkerActor])
  val donutStockWorkerRouterPool: ActorRef = context.actorOf(props, "DonutStockWorkerRouter")

  def receive = {
    case checkStock @ CheckStock(name) =>
      log.info(s"Checking stock for $name donut")
      donutStockWorkerRouterPool forward checkStock
  }
}
object TestDonoutActor extends App {

  val system = ActorSystem("DonutStoreActorSystem")

  val donutStockActor = system.actorOf(Props[DonutStockActor], name = "DonutStockActor")

  implicit val timeout = Timeout(5 second)

  val vanillaStockRequests = (1 to 10).map(_ => (donutStockActor ? CheckStock("vanilla")).mapTo[Int])
  for {
    results <- Future.sequence(vanillaStockRequests)
  } yield println(s"vanilla stock results = $results")

  Thread.sleep(5000)


}