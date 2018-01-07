// access modifiers augmented with qualifiers
class Outer1 {
  class Inner1 {
    private[Outer1] def f:Unit = println("f")
    class InnerMost1 {
      f
      val speed = 10
      private[Inner1] val distance = 90
    }
    (new InnerMost1).speed
    (new InnerMost1).distance
  }
   (new Inner1).f
}

package P1 {
  class Super1 {
    protected[P1] def f = println("f")
  }
  class Sub1 extends Super1 {
    f
  }
  class Other1 {
    (new Super1).f//accessible as f as protected upto P1
  }
}