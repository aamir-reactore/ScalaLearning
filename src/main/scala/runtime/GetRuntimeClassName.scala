package runtime

import scala.reflect.{ClassTag, classTag}

object GetRuntimeClassNameTest extends App {
  case class SomeEntity(id:Long)
  def getRT[E <: SomeEntity:ClassTag](entity:E) = {
    val clazzEntity: Class[_] = classTag[E].runtimeClass
    println(clazzEntity.getSimpleName)
  }
  class MyEntity(override val id:Long) extends SomeEntity(id)
  val e = new MyEntity(10)
  getRT(e)
}