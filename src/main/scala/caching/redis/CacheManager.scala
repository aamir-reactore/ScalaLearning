package caching.redis

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

case class SomeBaseEntity[Int,Long](id:Long,isRemoved:Boolean)
trait CacheManager {

//  def get[V](key: String)(implicit m: Manifest[V]): Option[V]

//  def put[V](key: String, value: V, ttl: Option[Duration])

  /**
   * Saves the object to the cache using
   * b.a built in key generation of type prefixing
   */
  def put(hashName: String, key: String, value: Any, ttl: Option[Duration] = None)

  def putAll[V <:SomeBaseEntity[_,Long]](hashName: String, value: List[V], ttl: Option[Duration] = None)

  /**
   * SaveAddition
   */
//  def saveAdditional(hashName: String, key: String, value: List[Any]): Boolean

  /**
    * get by key
   */
  def get[V](hashName: String, key: String)(implicit m: Manifest[V]):Option[V]

  def getCacheSize(hashName:String): Long

  def getWithFn[V <: SomeBaseEntity[_,Long]](hashName: String, fnToExecute: /*(Long,Long,Boolean)*/ => Future[V], key:Long,
                                       isIncludeRemoved: Boolean = false)(implicit m: Manifest[V]): Future[V]

  /**
    * get by key
    */
  def getAll[V](hashName: String)(implicit m: Manifest[V]):Option[List[V]]

  def getAllWithFn[V <: SomeBaseEntity[_,Long]](hashName: String,fnToExecute: /*(Boolean,Long)*/ => Future[Seq[V]],
                                          isIncludeRemoved: Boolean = false)(implicit m: Manifest[V]): Future[List[V]]

  //def getWithFn[V](key: String, elseFnToExecute: (Boolean) => Future[Seq[V]], isIncludeRemoved: Boolean = false): Future[Seq[V]]

  def clear(hashName: String, key: String):Boolean

  /**
   * remove All Details By its Key
   */
  def removeAll(hashName:String)

   def removeAll()

}

trait AbstractCacheManager extends CacheManager {

  def getWithFn[V <: SomeBaseEntity[_,Long]](hashName: String, fnToExecute: /*(Long,Boolean)*/ => Future[V], key:Long,
                                       isIncludeRemoved: Boolean = false)(implicit m: Manifest[V]): Future[V] = {
    //    val result = sync.get[Seq[V]](key)
    val cacheResult = get[V](hashName,key.toString)
    if (cacheResult.isDefined) {
      if (isIncludeRemoved || !cacheResult.get.isRemoved)
        Future.successful(cacheResult.get)
      else {
        val msg = s"No such entity with the given id : ${key} in the cache Map : ${hashName}"
        Future.failed(new Exception(msg))
      }
    }
    else {
      val future = fnToExecute
      future.map { dbResult => {
        put(hashName, key.toString, dbResult)
        if (isIncludeRemoved || !dbResult.isRemoved) {
          dbResult
        } else {
          val msg = s"No such entity with the given id : ${key}, tried reading from DB since cache is empty in class ${hashName}"
          throw new Exception(msg)
        }
      }
      }
    }
  }

  def getAllWithFn[V <: SomeBaseEntity[_,Long]](hashName: String, fnToExecute:/* (Boolean)*/ => Future[Seq[V]],
                                          isIncludeRemoved: Boolean = false)(implicit m: Manifest[V]): Future[List[V]] = {
    //    val result = sync.get[Seq[V]](key)
    val cacheResult = getAll[V](hashName)
    val result = if (cacheResult.isDefined && cacheResult.get.nonEmpty) Future.successful(cacheResult.get)
    else {
      val future = fnToExecute
      future.map { dbResult => {
        putAll[V](hashName, dbResult.toList)
        dbResult.toList
      }
      }
    }
    result.map { res =>
      if(isIncludeRemoved) {
        res
      }else {
        res.filter(_.isRemoved == false)
      }
    }
  }
}

/*

trait RedisCacheHelper extends CacheHelper {
  val cache: CacheManager = RedisCacheManager
}*/

trait CacheHelper {
  val cache: CacheManager
}

