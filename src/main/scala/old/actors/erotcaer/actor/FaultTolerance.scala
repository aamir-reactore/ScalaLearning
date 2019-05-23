package old.actors.erotcaer.actor

import old.actors.erotcaer.actor.patterns.{ProcessResult, WorkpileMaster, WorkpileWorker}
import akka.actor.{ActorRef, Props}
import akka.pattern.pipe
import akka.persistence.AtLeastOnceDelivery.{AtLeastOnceDeliverySnapshot, UnconfirmedWarning}
import akka.persistence._

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.{FiniteDuration, _}
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}

private[actor] case class Msg[I](deliveryId: Long, itemGroup: ItemGroup[I])
private[actor] case class Confirm(deliveryId: Long)
private[actor] case class Snap(deliverySnapshot: AtLeastOnceDeliverySnapshot)
private[actor] sealed trait PersistenceEvent
private[actor] case class MsgSent[I](cmd: ItemGroup[I]) extends PersistenceEvent
private[actor] case class MsgConfirmed(deliveryId: Long) extends PersistenceEvent

case class PersistentProps(id:String,
                           redeliverInterval: FiniteDuration = 10.seconds,
                           warnAfterNumberOfUnconfirmedAttempts: Int = 5,
                           redeliveryBurstLimit: Int = 10000,
                           batchSize:Int = 0) {

  def withBatchSize(newBatchSize:Int) = {
    this.copy(batchSize = newBatchSize)
  }

  def withRedeliverInterval(redeliverInterval:FiniteDuration) = {
    this.copy(redeliverInterval = redeliverInterval)
  }
}


case object Shutdown

private[actor] class RPersistentActor[I](destination: ActorRef,props:PersistentProps)
  extends  RAtLeastOnceDelivery with BatchableHelper {

  override def persistenceId: String = props.id

  override def warnAfterNumberOfUnconfirmedAttempts: Int = props.warnAfterNumberOfUnconfirmedAttempts

  override def redeliverInterval: FiniteDuration =  props.redeliverInterval

  override def redeliveryBurstLimit: Int = props.redeliveryBurstLimit

  override def batchSize: Int = props.batchSize

  var md: Option[SnapshotMetadata] = None

  override def receiveCommand: Receive = {
    case cmd: BatchableCommand[I] @unchecked    => {
      cmd.groupedItems.foreach(group => {
        persist(MsgSent(group))(updateSnapshot)
      })
    }
    case Confirm(deliveryId) => {
      persist(MsgConfirmed(deliveryId))(updateSnapshot)
    }
    case UnconfirmedWarning(warnings) => println(s"${props.id} warnings",warnings)
    case Shutdown =>  context.stop(self)
    case SaveSnapshotSuccess(metadata) => {
      md = Some(metadata)
    }
  }

  override def receiveRecover: Receive = {
    case SnapshotOffer(_, Snap(deliverySnapshot)) => {
      setDeliverySnapshot(deliverySnapshot)
    }
  }

  def updateSnapshot(event: PersistenceEvent): Unit = event match {
    case MsgSent(itemGroup) => {
      deliver(destination.path)(deliveryId => Msg(deliveryId, itemGroup))
      manageSnapshots
      //saveSnapshot(Snap(getDeliverySnapshot))
    }
    case MsgConfirmed(deliveryId) => {
      confirmDelivery(deliveryId)
    }
    case anything => println(anything)
  }


  override def confirmDelivery(deliveryId: Long): Boolean = {
    val status = super.confirmDelivery(deliveryId)
    deleteMessages(deliveryId)

    manageSnapshots
    status
  }

  def manageSnapshots = {
    val snapShot = getDeliverySnapshot
    // getDeliverySnapshot
    if (md.isDefined){
      deleteSnapshot(md.get.sequenceNr)
      md = None
    }

    if(snapShot.unconfirmedDeliveries.isEmpty) {
      deleteSnapshots(SnapshotSelectionCriteria(Long.MaxValue,Long.MaxValue,Long.MinValue,Long.MinValue))
    } else  saveSnapshot(Snap(snapShot))
  }

  override def redeliverOverdue(): Unit = {
   // println("in redeliverOverdue =>" + getDeliverySnapshot.unconfirmedDeliveries)
    tryProcessBatches()
    super.redeliverOverdue()
  }
}

trait AbstractExactlyOnceDeliveryActor[I] extends BaseActor {

  def props:PersistentProps

  var persitanceActor:Option[ActorRef] = None

  override def preStart: Unit = {
    persitanceActor = Some(context.actorOf(Props(classOf[RPersistentActor[I]],self,props)))
  }

  override def postStop: Unit = {
    if(persitanceActor.isDefined)
    persitanceActor.get ! Shutdown
  }

   receiver {
     case cmd: BatchableCommand[I] @unchecked => {
       if(persitanceActor.isDefined)
         persitanceActor.get ! cmd
       else self ! cmd // just in case if persitanceActor is not initialized by the time it receives command
     }
     case Shutdown =>  context.stop(self)
  }
}

trait ExactlyOnceDeliveryActor[I] extends AbstractExactlyOnceDeliveryActor[I] {

  def onItemsReceived(group:ItemGroup[I]) : Future[_]

  receiver {
    case msg: Msg[I] @unchecked => {
      val result = Try(onItemsReceived(msg.itemGroup))
      if(result.isSuccess)
        result.get map { res =>
          // println("on future complete =>" + res)
          Confirm(msg.deliveryId)
        } pipeTo sender()
      else {
        println(result.failed.get.getMessage,result.failed.get)
      }
    }
  }
}

/************ Master-worker approach *****************/

private[actor] class ExactlyOnceDeliveryMasterActor[I](override val props: PersistentProps)
  extends WorkpileMaster[Msg[I]] with AbstractExactlyOnceDeliveryActor[I] {

  val busyProcessingIds:ListBuffer[Long] = ListBuffer()

  receiver {
    case msg: Msg[I] @unchecked => {
      val isDeliveryIdInBufferAlready = busyProcessingIds.contains(msg.deliveryId)
      if(!isDeliveryIdInBufferAlready) {
        enqueueWork(msg)
        busyProcessingIds.append(msg.deliveryId)
      }
    }
  }
  //To avoid message if it is already in queue
  override def interceptProcessCompletion(processResult:ProcessResult): Unit = {
    val deliveryId = processResult.identifier.asInstanceOf[Long]

   if(processResult.isSuccess)
    persitanceActor.get ! Confirm(deliveryId)

    busyProcessingIds -= deliveryId
  }

}

abstract class ExactlyOnceDeliveryWorkerActor[I](override val master: ActorRef) extends WorkpileWorker[Msg[I]](master) {

  def onItemsReceived(group:ItemGroup[I]) : Future[_]

  override def process(cmd: Msg[I]): Unit = {
    val result = Try(onItemsReceived(cmd.itemGroup))

    if(result.isSuccess)
      result.get.onComplete({
        case Success(res) => ready(Some(ProcessResult(cmd.deliveryId,isSuccess = true)))
        case Failure(ex) => ready(Some(ProcessResult(cmd.deliveryId,isSuccess = false)))
      })
    else {
      master ! ready(Some(ProcessResult(cmd.deliveryId,isSuccess = false)))
      println(result.failed.get.getMessage,result.failed.get)
    }
  }
}

abstract class ExactlyOnceDeliveryWorkerActorComponent extends SuperActors with RootSupervisorHelper {
  self: CoreActorSystem =>

  def createMasterWorkers[T <:ExactlyOnceDeliveryWorkerActor[I] : ClassTag,I](persistentProps: PersistentProps,
                                                                              clazz: Class[_],
                                                                              nrOfInstances: Int = availableProcessors,
                                                                              name:String
                                                                               ): MasterWorkerContainer = {
   // val finalName = classTag[T].runtimeClass.getName
    val workerClazz = classOf[ExactlyOnceDeliveryMasterActor[I]]
    val master = actorOf[ExactlyOnceDeliveryMasterActor[I]](Props(workerClazz,persistentProps),Some(name))
    val workers: Seq[ActorRef] =  0 to nrOfInstances map { i =>
     // val name =  workerClazz.getSimpleName
      actorOf[T](Props(clazz,master),Some(name + "_worker_" + i))
    }
    MasterWorkerContainer(master,workers.toList)
  }
}

object ExactlyOnceDeliveryWorkerActor extends ExactlyOnceDeliveryWorkerActorComponent with BootedCoreActorSystem

case class MasterWorkerContainer(master:ActorRef, workers:List[ActorRef])


   