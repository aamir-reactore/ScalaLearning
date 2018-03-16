package implicits.scaladocexamples

object Program1Test extends App {
  def foo[T](t: T)(implicit integral: Integral[T]): Unit = {
    println(integral)
    println(t)
  }
  foo(12)
}