package old.actors.erotcaer.actor

import scala.language.existentials

case class ItemGroup[I](groupName:String,values:List[I])
private[actor] case class UnconfirmedDelivery(deliveryId: Long, group: ItemGroup[_])
private[actor] case class BatchResult(batches: List[ItemGroup[_]], removableDeliveryIds: List[Long])

private[actor] trait BatchableHelper {
  this: RPersistentActor[_] =>

  def batchSize:Int

  def tryProcessBatches() = {
    val unconfirmedValues: Seq[UnconfirmedDelivery] = getDeliverySnapshot.unconfirmedDeliveries.map {
      u => UnconfirmedDelivery(u.deliveryId, u.message.asInstanceOf[Msg[_]].itemGroup)
    }.toList
    val batchResult = processUnconfirmedDeliveries(unconfirmedValues.toList)
    batchResult.batches.foreach { group =>
      persist(MsgSent(group))(updateSnapshot)
    }
    //remove rest of the smaller groups by confirming them
    batchResult.removableDeliveryIds.foreach { deliveryId =>
      confirmDelivery(deliveryId)
    }

    if(unconfirmedValues.isEmpty && batchResult.removableDeliveryIds.isEmpty && batchResult.batches.isEmpty) {
      manageSnapshots
    }

  }

  private def processUnconfirmedDeliveries(deliveryGroups: List[UnconfirmedDelivery]): BatchResult = {

    val batchables = deliveryGroups.filter(d => d.group.values.size < batchSize)
    val removableDeliveryIds = batchables.map(_.deliveryId)

    val flattened = batchables.map(_.group)
      .map(group => ItemGroup(group.groupName,group.values))
      .groupBy(_.groupName)
      .map(f => (f._1,f._2.flatMap(_.values)))

    val refinedGroups: Seq[ItemGroup[_]] = flattened.flatMap(group => {
      group._2.grouped(batchSize).map(newGroup => ItemGroup(group._1, newGroup))
    }).toList

    BatchResult(refinedGroups.toList,removableDeliveryIds)
  }
}

trait BatchableCommand[I] {
  val items: List[I]
  def groupedItems: List[ItemGroup[I]] = {
    if (groupBy.isDefined)
      items.groupBy(groupBy.get).map(g => ItemGroup(g._1.toString, g._2)).toList
    else
      List(ItemGroup(this.getClass.getSimpleName, items))
  }
  def groupBy[K]: Option[(I) => Any]
}