package martinoderskybook.classesfieldsmethods
import scala.collection.mutable.Map
//Singleton or Standalone object
/**
  ~~> a class connot extend an object
  ~~> an object cannot extends object
 */
object CheckSumAccumulator {
 private val cache = Map.empty[String,Int]

  def calculate(s:String) = {
    if(cache.contains(s))
      cache(s)
    else {
      val acc = new CheckSumAccumulator
      for(x <- s)
        acc.add(x.toByte)
        val cs = acc.checksum
      cache += (s  -> cs)
      cs
    }
  }
}
class CheckSumAccumulator {
  private var sum = 0

  def add(b:Byte) = {
    sum += b
  }
  def checksum:Int = ~(sum & 0xFF) + 1
}

object Program2 extends App {
   val res = CheckSumAccumulator.calculate("yellow world")
  println(res)
 //as if companion class's object
  val acc = CheckSumAccumulator

  val acc2 = new CheckSumAccumulator
  println(acc2)
  println(acc)
}