package selftypes

trait SelfAware { self =>
  val me = self
  
  def method1 = {
    println("called method1")
  }
}
object selftest extends App {

  class X

  val s = new X with SelfAware
  println(s.me.method1)
}