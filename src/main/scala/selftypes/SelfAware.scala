trait SelfAware { self =>
  val me = self
  
  def method1 = {
    println("called method1")
  }
}

class X
val s = new X with SelfAware
println(s.me.method1)