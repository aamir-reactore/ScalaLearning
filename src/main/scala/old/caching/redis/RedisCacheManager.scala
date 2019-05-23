package old.caching.redis

import com.redis.serialization._
import com.redis.{RedisClient, RedisClientPool}
import json.BaseJsonUtilitiesComponent
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

object RedisCacheConnector {
  private val redisServer =  ""//UnCachedZooKeeperHelper.getRedisServer
  private val redisPort = ""
  private val redisDatabase = ""
  val cacheEnabled = true //ZooKeeperHelper.isRedisCacheEnabled

  val clients = new RedisClientPool(redisServer, redisPort.toInt, database = redisDatabase.toInt)
  //val l = (0 until 5000).map(_.toString).toList
}

trait RedisCacheConnectorComponent {
  val clients: RedisClientPool
}

trait RedisCacheConnector extends RedisCacheConnectorComponent {
  override val clients: RedisClientPool = RedisCacheConnector.clients
}


trait RedisCacheManager extends AbstractCacheManager  with JsonSerializer {
  this: BaseJsonUtilitiesComponent with RedisCacheConnectorComponent =>

  override def put(hashName: String, key: String, value: Any, ttl: Option[Duration] = None) = {
    clients.withClient {
      client =>
        removeAll(hashName)
      //client.hset(hashName, key, serialize(value))
    }
  }

  /**
    * Saves the object to the cache using
    * a built in key generation of type prefixing
    */
  override def putAll[V <: SomeBaseEntity[_, Long]](hashName: String, values: List[V], ttl: Option[Duration] = None) = {
    clients.withClient {
      client =>
        values.foreach(v => client.hset(hashName, v.id, serialize(v)))
    }
  }

  /*
    override def put[V](key: String, value: V, ttl: Option[Duration]): Unit = ??? {
      clients.withClient {
        client => client.set(key, serialize(value))
      }
    }*/
  /*
    override def get[V](key: String)(implicit m: Manifest[V]): Option[V] = ??? {
      clients.withClient {
        client => {
          val value = client.get(key)
          if (value.isEmpty) None
          else
          getValueOrReturnNoneIfExtractionIsFailed[V](deserialize[V](value.get))
        }
      }
    }*/

  override def get[V](hashName: String, key: String)(implicit m: Manifest[V]): Option[V] = {
    import Parse.Implicits._
    println("Get result By Id from  the Redis Cache " + hashName + "  " + key)
    clients.withClient {
      client =>
        val value = client.hget[String](hashName, key)
        if (value.isEmpty) None
        else
          getValueOrReturnNoneIfExtractionIsFailed[V](deserialize[V](value.get))
    }
  }

  private def getValueOrReturnNoneIfExtractionIsFailed[V](triedValue: Try[V]): Option[V] = {
    triedValue match {
      case Success(value) => Some(value)

      /** Note: If extraction fails, then probably it id outdated entity structure - then delete the value from redis and return None **/
      case Failure(ex) => {
        None
        //Do nothing
        //        clients.withClient {
        //          client => {
        //            if (hashName.isDefined) {
        //              if (hashName.isDefined)
        //                client.hdel(hashName.get, key.get)
        //              else client.del(hashName.get)
        //            } else client.del(key)
        //          }
        //
        //        }
      }
    }
  }

  override def getCacheSize(hashName: String): Long = {
    clients.withClient { client =>
      client.hlen(hashName).get
    }
  }

  override def getAll[V](hashName: String)(implicit m: Manifest[V]): Option[List[V]] = {
    import Parse.Implicits._
    println("Get All Result from the Redis Cache " + hashName)
    clients.withClient {
      client =>
        val getAllRes = client.hgetall[String, String](hashName)
        if (getAllRes.isDefined) {

          if (getAllRes.get.values.nonEmpty) {
            val deserializedResults = getAllRes.get.values.toList.map(v => getValueOrReturnNoneIfExtractionIsFailed(deserialize[V](v)))
            //Check if it got any corrupted values, then return None as it need to be replaced
            if (deserializedResults.exists(_.isEmpty)) {
              None
            } else {
              Some(deserializedResults.map(_.get))
            }
          }
          else None
        } else None
    }
  }

  /**
    * clear cache by its key
    *
    * @param key
    */
  override def clear(hashName: String, key: String): Boolean = {
    /*    logger.debug("Delete from the Redis Cache with" + key + " And Field")
        clients.withClient {
          client =>
            val result = client.hdel(hashName, key)
            val deleteRes = if (result.isDefined && result.get == 1) true else false
            deleteRes
        }*/

    removeAll(hashName)
    true
  }

  /**
    * remove All Details By its Key
    */
  override def removeAll(hashName: String): Unit = {
    println("Delete ALL  from  the Redis Cache with key " + hashName)
    clients.withClient {
      client =>
        client.del(hashName)
    }
  }

  override def removeAll(): Unit = {
    clients.withClient {
      client =>
        client.flushall
    }
  }
}

object ll extends App {
  val client = new RedisClient("127.0.0.1", 6379)
  val map: Option[Map[String, String]] = client.hgetall("testhmset")
  println(map.get)



}