package runtime

class DepeResolTest {
  def printHello = println("hello")
}
object DependencyResolver {
  implicit class DependencyResolverExtension(val path:String) {

    def resolveDependency[T](): T = {
      Class.forName(path).newInstance.asInstanceOf[T]
    }
  }
}

object dd extends App {
  import DependencyResolver._
  val res = "DepTest".resolveDependency[DepeResolTest]()
  println(res.printHello)
}