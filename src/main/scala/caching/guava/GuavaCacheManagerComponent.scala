package caching.guava

import com.google.common.cache.CacheBuilder
import org.joda.time.DateTime

import scala.collection.mutable
import scala.concurrent.duration.Duration
import scalacache.{Entry, ScalaCache}
//import scalacache.caching.guava.Entry

 trait GuavaCacheManagerComponent {

  import scalacache.guava.GuavaCache
  val underlying = CacheBuilder.newBuilder().maximumSize(10000L).build[String, Object]
  implicit val scalaCache = ScalaCache(GuavaCache(underlying))

  private def toExpiryTime(ttl: Duration): DateTime = DateTime.now.plusMillis(ttl.toMillis.toInt)
/*
  /**
    * Saves the object to the cache using
    * a built in key generation of type prefixing
    */
  def put(hashName: String, key: String, value: Any, ttl: Option[Duration] = None): Unit = {

    val table = getTable(hashName)
    val tableNew = table.+(key -> value)
    //    table += (key -> value)
    val entry = Entry(tableNew, ttl.map(toExpiryTime))
    removeAll(hashName)
    //underlying.put(hashName, entry.asInstanceOf[Object])

//    val ttlMsg = ttl.map(d => s" with TTL ${d.toMillis} ms") getOrElse ""
//    logger.debug(s"Inserted value into cache with key $key$ttlMsg")
  }

  /**
    * remove All Details By its Key
    */
  def removeAll(hashName: String): Unit = underlying.invalidate(hashName)

   def removeAll(): Unit = underlying.invalidateAll()

  /**
    * get by key
    */
   def get[V](hashName: String, key: String)(implicit m: Manifest[V]): Option[V] = {

    val value = getTable(hashName).get(key)
    value.map(v => v.asInstanceOf[V])
  }

  import scala.concurrent.duration._

   def clear(hashName: String, key: String): Boolean = {
//    val table = getTable(hashName)
//    val tableNew = table.-(key)
//    val expiryTime = Some(toExpiryTime(20.days))
//    saveTableToCache(hashName, tableNew)
//    true
    removeAll(hashName)
    true
  }

   def getCacheSize(hashName: String): Long = getTable(hashName).size
case class SomeEntity[Int,Long](id:Long)
   def putAll[V <: SomeEntity[_, Long]](hashName: String, value: List[V], ttl: Option[Duration] = None): Unit = {
    val values = value.map(v => v.id.toString -> v).toMap[String, Any]
    val mutableValues = mutable.Map(values.toSeq: _*)
    saveTableToCache(hashName, mutableValues, ttl)
  }

  /**
    * get by key
    */
   def getAll[V](hashName: String)(implicit m: Manifest[V]): Option[List[V]] = {
    val values = getTable(hashName).map(_._2.asInstanceOf[V])
    if (values.nonEmpty) Some(values.toList) else None
  }

  private def getTable(hashName: String): mutable.Map[String, Any] = {

    val entry = Option(underlying.getIfPresent(hashName).asInstanceOf[Entry[mutable.Map[String, Any]]])
    /*
     Note: we could delete the entry from the cache if it has expired,
     but that would lead to nasty race conditions in case of concurrent access.
     We might end up deleting an entry that another thread has just inserted.
     */
    val result = entry.flatMap { e =>
      if (e.isExpired) None
      else Some(e.value)
    }
    if (result.isDefined) result.get else mutable.Map[String, Any]()
  }

  private def saveTableToCache(hashName: String, table: mutable.Map[String, Any],
                               ttl: Option[Duration] = None): Unit = {
    val entry = Entry(table, ttl.map(toExpiryTime))
    underlying.put(hashName, entry.asInstanceOf[Object])
  }*/
}

object GuavaCacheManager /*extends GuavaCacheManager */{
  val guavaCacheManager = new GuavaCacheManagerComponent {}
}

trait GuavaCache {
//  private val gCache = CacheBuilder.newBuilder.build[String, Object]
  val cache = GuavaCacheManager.guavaCacheManager //new GuavaCacheManager {}
}

object GuavaCacheImpl {
  val underlyingGuavaCache = GuavaCacheManager.guavaCacheManager.underlying //CacheBuilder.newBuilder().maximumSize(10000L).build[String, Object]
  implicit val scalaCache = ScalaCache(scalacache.guava.GuavaCache(underlyingGuavaCache))
}