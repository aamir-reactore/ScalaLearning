package caching.redis

import json.BaseJsonUtilitiesComponent

import scala.util.Try

/**
  * Created by Muralig on 2/13/2016.
  */

trait CacheSerializer {
  def serialize(msg:Any):Any
  def deserialize[V](msg:Any)(implicit m: Manifest[V]):Try[V]
}

trait JsonSerializer extends CacheSerializer {
  this: BaseJsonUtilitiesComponent =>
  override def serialize(msg: Any): Any = msg.asJson

  override def deserialize[V](msg: Any)(implicit m: Manifest[V]): Try[V] = {
     extractEntityWithTry[V](msg.toString)
  }
}

trait ByteSerializer extends CacheSerializer {

  override def serialize(msg: Any): Any = ???

  override def deserialize[V](msg: Any)(implicit m: Manifest[V] = null): Try[V] = ???
}
