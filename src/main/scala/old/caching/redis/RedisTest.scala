package old.caching.redis

import com.redis.RedisClient

object RedisTest1 extends App {
    val client = new RedisClient("127.0.0.1", 6379)
    val map: Option[Map[String, String]] = client.hgetall("testhmset")
    println(map.get)

}