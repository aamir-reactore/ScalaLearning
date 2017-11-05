package akfak

import akka.Done
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.kafka.ConsumerMessage.CommittableOffsetBatch
import akka.kafka.scaladsl.Consumer
import akka.kafka.scaladsl.Consumer.Control
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import json.BaseJsonUtilities._
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}
import org.joda.time.DateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.{FiniteDuration, _}
import scala.reflect.Manifest
case class ServiceBindingCompleted(isBound:Boolean)
trait CreateDate {
  val createDate: DateTime
}

trait SomeEvent extends CreateDate {
  override val createDate: DateTime = DateTime.now
  val mineId: Long
}
object ActorSystemContainer  {
  lazy val system: ActorSystem =  ActorSystem("ActorSystem")

  def publish[E <: SomeEvent](event:E) = {
    system.eventStream.publish(event)
  }

  def subscribe[E <: SomeEvent](actorRef: ActorRef, channel:Class[E]) = {
    system.eventStream.subscribe(actorRef, channel)
  }
}
trait KafkaConsumerComponent[M <:KafkaPublishEntity[_]] {

  implicit val manifest: Manifest[M]

  def onKafkaMsgReceived(messages: List[KafkaMessage[M]]): Future[_]

  val topics: List[String]
}
trait KafkaConsumer[M <:KafkaPublishEntity[_]] extends KafkaConsumerComponent[M] with MainActor {

  receiver({
    case ServiceBindingCompleted(isBound) => {
      println(
        s"""
           | ###################
           |
          | #### Started subscribing for ${kafkaProps.groupId}
           |
          | ###################
        """.stripMargin)
      init()
    }
  })

  override def preStart(): Unit = {
    super.preStart()
    context.system.eventStream.subscribe(self,classOf[ServiceBindingCompleted])
  }

  def kafkaProps: KafkaProps = KafkaProps(getClass.getCanonicalName)

  implicit val system: ActorSystem = ActorSystemContainer.system
  implicit val fm = ActorMaterializer()

  //
  //  system.scheduler.scheduleOnce(2.seconds, new Runnable {
  //    override def run(): Unit = init()
  //  })

  def init() = {

    val consumerSettings: ConsumerSettings[Array[Byte], String] = ConsumerSettings(ActorSystemContainer.system, new ByteArrayDeserializer, new StringDeserializer)
      .withBootstrapServers(kafkaProps.serverUrl)
      .withGroupId(kafkaProps.groupId)
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    val src: Source[Done, Control] = Consumer.committableSource(consumerSettings, Subscriptions.topics(topics: _*))
      .groupedWithin(kafkaProps.batchSize, kafkaProps.waitPeriod)
      .mapAsync(1) { group =>
        val msgs = group.toList.map({ elem =>
          val extracted = extractEntityWithTry[KafkaMessage[M]](elem.record.value())
          if(extracted.isSuccess) {
            extracted.toOption
          } else {
            println(
              """
                |
                | extraction failed while consuming
                |
              """.stripMargin, extracted.failed.get)
            println(extracted.failed.get)
            None
          }
        })
        val x: Future[_] = processOnReceived(msgs.filter(_.isDefined).map(_.get))
        val r= x.map { y =>
          group
        }
        r
      }
      .map(group => group.foldLeft(CommittableOffsetBatch.empty) { (batch, elem) => batch.updated(elem.committableOffset) })
      .mapAsync(3)(_.commitScaladsl())

    src.runWith(Sink.ignore)
  }

  private def processOnReceived(msgs: List[KafkaMessage[M]]): Future[_] = {
    onKafkaMsgReceived(msgs)
  }

  //def logOnException(msgs: List[KafkaMessage[M]])
}

case class ConsumedMessage(topicId: String, value: String) {

}

object ConsumedMessage {
  def empty = {
    ConsumedMessage("", "")
  }
}


class KafkaProps(val groupId: String,
                 val serverUrl: String,
                 val batchSize: Int = 500,
                 val waitPeriod: FiniteDuration = 10.seconds,
                 val retryingInterval: FiniteDuration = 5.minutes,
                 val ignorableExceptions:List[Exception] = Nil) {

  def withBatchSize(batchSize: Int) = {
    copy(batchSize = batchSize)
  }

  def withWaitPeriod(waitPeriod: FiniteDuration) = {
    copy(waitPeriod = waitPeriod)
  }

  def withGroupId(groupId: String) = {
    copy(groupId = ResolveAddressUtility.getAddress+"-"+groupId)
  }

  def withRetryInterval(retryingInterval: FiniteDuration) = {
    copy(retryingInterval = retryingInterval)
  }

  def withIgnorableException(ignorableExceptions:List[Exception]) = {
    copy(retryingInterval = retryingInterval)
  }
  def withServerUrl(serverUrl: String) = {
    copy(serverUrl = serverUrl)
  }

  private def copy(groupId: String = groupId,
                   serverUrl: String = serverUrl,
                   batchSize: Int = batchSize,
                   waitPeriod: FiniteDuration = waitPeriod,
                   retryingInterval: FiniteDuration = retryingInterval,
                   ignorableExceptions:List[Exception] = ignorableExceptions
                  ): KafkaProps =
    new KafkaProps(groupId, serverUrl, batchSize, waitPeriod, retryingInterval,ignorableExceptions)

}

object KafkaProps {

  def apply(groupId: String, serverUrl: String): KafkaProps = {
    new KafkaProps(ResolveAddressUtility.getAddress+"-"+groupId, serverUrl)
  }

  def apply(groupId: String) = {
    new KafkaProps(ResolveAddressUtility.getAddress+"-"+groupId, ConfigHelper.KAFKA_CONNECTION_STRING)
  }
}
case class PubSubTest(name:String, age:Int) extends KafkaPublishEntity[Long] {
  topicId = "test-topic"
}

object sll extends App with KafkaProducerClient {

  def publishToKafka() {
    for {
      result <- produceAsync("test-topic", KafkaMessage(1L, List(PubSubTest("johny", 34)), topicId = "test-topic"))
      _=println("result === " + result)
    } yield result
  }
  publishToKafka()
}

class XX extends KafkaConsumer[PubSubTest] {
  override lazy val topics: List[String] = List("test-topic")
  override implicit val manifest: Manifest[PubSubTest] = Manifest.classType(classOf[PubSubTest])
  override def onKafkaMsgReceived(messages: List[KafkaMessage[PubSubTest]]): Future[Boolean] = {
    val res: PubSubTest = messages.flatMap(x =>x.payload).head
    println("came here >>>>>>>>>>>>>>>>>>>>>>> + " + res)
    Future.successful(true)
  }
}

object jj extends App {
  val xx = ActorSystemContainer.system.actorOf(Props[XX], "YY")
  xx ! ServiceBindingCompleted(true)
}