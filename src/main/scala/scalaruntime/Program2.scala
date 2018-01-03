package scalaruntime
import scala.reflect.runtime.universe._
/**
  * Refer:
  * https://docs.scala-lang.org/overviews/reflection/typetags-manifests.html
  */
object Program2Test1 extends App {

  def paramInfo[T](x: T)(implicit tag: TypeTag[T]):Unit = {
    val tags = tag.tpe match { case TypeRef(_, _, args) => args}
    println(s"type of $x has type arguments $tags")
  }
  paramInfo(42)
  paramInfo(List(1, 2))

}
object Program2Test2 extends App {

  def paramInfo[T: TypeTag](x: T): Unit = {
    val targs = typeOf[T] match {
      case TypeRef(_, _, args) => args
    }
    println(s"type of $x has type arguments $targs")
  }

  paramInfo(42)

  paramInfo(List(1, 2))
}
