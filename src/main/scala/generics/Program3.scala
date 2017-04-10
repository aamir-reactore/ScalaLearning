package generics

import scala.collection.immutable.HashSet
import scala.util.control.NonFatal

object Test extends App {

  def listOfTwo[T, R](first: T, second: R): Seq[Any] = List(first, second)

  val l = listOfTwo[Int, String](1, "abc")
  println(l)

  def loop = throw new Exception("aa")
  def nullIfFailed[T >: Null](expr: => T): T =
    try expr catch {
      case NonFatal(_) => null
    }

  nullIfFailed(loop)


  type myType = Map[Int, String]

  def x(abc:Int):myType = Map(1 -> "abc")

  trait Base {
    type T

    def method: T
  }

  class Implementation extends Base {
    type T = Int

    def method: T = 42
  }
}
