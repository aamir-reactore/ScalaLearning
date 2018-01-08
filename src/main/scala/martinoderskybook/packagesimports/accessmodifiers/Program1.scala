class Outer {
  class Inner {
    private def f:Unit = println("f")
    class InnerMost {
      f //accessible here
       val speed = 10
      private val distance = 90
    }
    (new InnerMost).speed
    // inaccessible  private member of inner class(new InnerMost).distance
  }
  // inaccessible (new Inner).f coz f is private in class Inner
}