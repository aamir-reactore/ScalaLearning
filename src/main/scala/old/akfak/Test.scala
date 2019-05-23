
package old.akfak


import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigFactory
import json.BaseJsonUtilities._
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
object ConfigHelper {
  val conf = ConfigFactory.load

  val ZK_CONNECTION_STRING = conf.getString("zookeeper.connectionString")
  val KAFKA_CONNECTION_STRING = conf.getString("kafka.kafkaUrl")
}

trait Topic[TId] {
  @(Alias)(canResolveType = false, entity = "string")
  var topicId: String = this.getClass.getName

  def setTopicId(topicId: String): Unit = {
    this.topicId = topicId
  }

  def isIgnore: Boolean = false
}
trait Publishable[TId] extends Topic[TId]
trait KafkaPublishEntity[TID <: Long] extends Publishable[TID] {
  val index: BigInt = {
    Thread.sleep(1) // just to make the millisecond value unique
    System.currentTimeMillis()
  }
}
case class KafkaMessage[E <: KafkaPublishEntity[_]](mineId:Long, payload:List[E], exception: Option[Throwable] = None, topicId:String = "") {
  val lastIndex = payload.last.index

  def ++ (that: KafkaMessage[E]) = {
    KafkaMessage[E](this.mineId, this.payload ++ that.payload)
  }
}
trait KafkaProducerClientComponent {

  def produceAsync(topicId: String, records: KafkaMessage[_]): Future[Boolean]

  def produceAsync(topicId: String, records: String): Future[Boolean]

}

 object KafkaProducerClientHelper {
  val producer = KafkaUtil.createNewKafkaProducer(ConfigHelper.KAFKA_CONNECTION_STRING, acks = 1)
}

trait KafkaProducerComponent {
  def producer: KafkaProducer[Array[Byte], Array[Byte]]
}

trait AbstractKafkaProducerClient extends KafkaProducerClientComponent {
  this: KafkaProducerComponent  =>


  override def produceAsync(topicId: String, records: KafkaMessage[_]): Future[Boolean] = {

    val serialized = records.asJson
    val (p: Promise[(RecordMetadata, Exception)], reqMetadata: RecordMetadata) = sendToKafka(topicId, serialized.getBytes)
    //val (callbackMeta, callbackEx) = Await.result(p.future, new FiniteDuration(3, TimeUnit.SECONDS))

    for {
      (callbackMeta, callbackEx) <- p.future
    } yield {
      if (IsDelivered(reqMetadata, callbackMeta, callbackEx))
        true
      else {
         println("Delivery failed, please check whether Kafka is running or you are pointing to the right URL")
        false
      }
    }
  }

  override def produceAsync(topicId: String, serializedJson: String): Future[Boolean] = {

    val (p: Promise[(RecordMetadata, Exception)], reqMetadata: RecordMetadata) = sendToKafka(topicId, serializedJson.getBytes)
    //val (callbackMeta, callbackEx) = Await.result(p.future, new FiniteDuration(3, TimeUnit.SECONDS))

    for {
      (callbackMeta, callbackEx) <- p.future
    } yield {
      if (IsDelivered(reqMetadata, callbackMeta, callbackEx))
        true
      else {
         println("Delivery failed, please check whether Kafka is running or you are pointing to the right URL")
        false
      }
    }
  }

  def sendToKafka(topicId: String, serialized: Array[Byte]): (Promise[(RecordMetadata, Exception)], RecordMetadata) = {
    val record: ProducerRecord[Array[Byte], Array[Byte]] = new ProducerRecord(topicId, 0, "key".getBytes, serialized)
    val p = Promise[(RecordMetadata, Exception)]()
    val response = producer.send(record, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        p.success((metadata, exception))
      }
    })
    val reqMetadata = response.get(2, TimeUnit.SECONDS)
    (p, reqMetadata)
  }

  def IsDelivered(reqMetadata: RecordMetadata, callbackMeta: RecordMetadata, callbackEx: Exception): Boolean = {
    reqMetadata.offset() == callbackMeta.offset() &&
      reqMetadata.topic() == callbackMeta.topic() &&
      reqMetadata.partition() == callbackMeta.partition() &&
      callbackEx == null
  }
}

trait KafkaProducerImpl extends KafkaProducerComponent {
  override val  producer = KafkaProducerClientHelper.producer
}
trait KafkaProducerClient extends AbstractKafkaProducerClient with KafkaProducerImpl

object ll extends App with KafkaProducerClient {
case class MqttData(name:String, age:Int) extends  KafkaPublishEntity[Long] {
  topicId = "test-topic"
}
  def publishToKafka() {
    for {
      result <- produceAsync("test-topic", KafkaMessage(1L, List(MqttData("johny", 34)), topicId = "test-topic"))
      _=println("result === " + result)
    } yield result
  }
  publishToKafka()
  Thread.sleep(10000)
}