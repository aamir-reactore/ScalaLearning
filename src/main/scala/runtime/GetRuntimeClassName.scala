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


class A {

   def getRuntimeClass = this.getClass.getSimpleName
}

object ATest extends App {

  println("The line number is " + new Exception().getStackTrace()(0).getLineNumber)

}
object ClassName {
    private def className[A](a: A)(implicit m: Manifest[A]) = m.toString
    override def toString = className(this)
}