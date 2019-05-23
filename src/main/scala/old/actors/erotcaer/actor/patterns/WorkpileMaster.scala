package old.actors.erotcaer.actor.patterns


import old.actors.erotcaer.actor.BaseActor
import akka.actor.{ActorRef, Terminated}

import scala.collection.mutable

/** @author Stephen Samuel
  * https://github.com/sksamuel/akka-patterns
  * */
abstract class WorkpileMaster[C] extends BaseActor {

  val queue = mutable.Queue.empty[Any]
  val workers = mutable.Queue.empty[ActorRef]

  receiver {
    case WorkerReady(worker,result) =>
      if(result.isDefined) {
        interceptProcessCompletion(result.get)
      }
      if (queue.isEmpty) {
        workers enqueue worker
        context.watch(worker)
      } else {
        worker ! WorkCommand(queue.dequeue())
      }

    case Terminated(worker) =>
      workers.dequeueFirst(_ == worker)
  }

  def enqueueWork(cmd: C): Unit = {
    if (workers.isEmpty) {
      queue.enqueue(cmd)
    } else {
      workers.dequeue() ! WorkCommand(cmd)
    }
  }

  def interceptProcessCompletion(processResult:ProcessResult): Unit = { } //do nothing
}

abstract class WorkpileWorker[C](val master: ActorRef) extends  BaseActor {

  override def preStart() = {
    ready()
  }

  receiver {
    case work: WorkCommand[C]  @unchecked =>
      process(work.work)
  }

  def ready(processResult:Option[ProcessResult] = None) = master ! WorkerReady(self,processResult)

  def process(cmd: C)
}

private[patterns] case class WorkerReady[ID](worker: ActorRef, processResult:Option[ProcessResult])

case class WorkCommand[C](work: C)

 case class ProcessResult(identifier:Any,isSuccess:Boolean)