package martinoderskybook.compositioniheritance

object MultipleInheritance1 extends App {

  trait A {
    def display() {
      println("From A.display")
    }
  }

  trait B extends A {
    override def display() {
      println("From B.display")
    }
  }

  trait C extends A {
    override def display() {
      println("From C.display")
    }
  }

  class D extends B with C {}

  val d = new D
  println(s">>>>>>>>>${d.display}")
}

object MultipleInheritance2 extends App {
  trait A2 {
    def string = ""
  }
  trait B2 extends A2 {
    override def string = "B String" + super.string
  }
  trait C2 extends B2 {
    override def string = "C String" + super.string
  }

  class MultipleMixinM2 extends B2 with C2
  println(new MultipleMixinM2().string)
}
