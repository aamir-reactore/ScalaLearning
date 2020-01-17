//protected access
//protected only accessible to subclasses
package P {
  class Super {
    protected def f = println("f")
  }
  class Sub extends Super {
    f
  }
  class Other {
    //(new Super).f//, inaccessible to other than subclasses
  }
}
package Q {
  import P.{Super => MySuper}
  class QSub extends MySuper {
    f
  }
}