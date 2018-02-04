package ordering

/**
  * View and context bounds resources
  * https://stackoverflow.com/questions/4465948/what-are-scala-context-and-view-bounds
  * http://jatinpuri.com/2014/03/replace-view-bounds/
  */
object ViewBoundDeprecation1 extends App {

  implicit def a[T](n: T) = n match {
    case x: String => x.toInt
  }

  def foo[T <% Int](x: T): Int = x

  println(foo("24"))
}

object ViewBoundDeprecation2 extends App {
  /**
  A context bound describes an implicit value, instead of view bound's implicit conversion,
  It is used to declare that for some type A, there is an implicit value of type B[A] available
  */
  implicit def abc(x:String) = x.toInt
  type L[T] = T => Int
  def goo[T : L](x: T):Int = x

  println(goo[String]("22"))
}
object ViewBoundDeprecation3 extends App {
  implicit def abc(x:String) = x.toInt
  def goo[T : ({type L[X] = X => Int})#L](x: T):Int = x
  println(goo("24"))
}
object ViewBoundDeprecation4 extends App {

  implicit def abc(x:String) = x.toInt
  def foo[T,E](x: T)(implicit ev$1: T => E): E = x
  println(foo[String,Int]("24"))
}