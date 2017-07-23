package martinoderskyvideos.week4

trait Function1[A,B] {
  def apply(x:A):B
}

object FunctionsAsObjects1 extends App {
  val f: Function1[Int, Int] = new Function1[Int, Int] {
    def apply(x:Int):Int = x * x
  }
  val f1: (Int) => Int = (x:Int) => x * x
  println(f1(2))
  println(f1.apply(2))
  println(f(2))
  println(f.apply(2))
}

object FunctionsAsObjects2 extends App {

  //A function of 0 parameters.

  //In the following example, the definition of javaVersion is a shorthand for the anonymous class definition anonfun0:

  val javaVersion: () => String = () => sys.props("java.version")
   println(javaVersion())
  val anonfun0 = new (() => String) { // new Function0[String] {
    def apply(): String = sys.props("java.version")
  }
  println(anonfun0())
  assert(javaVersion() == anonfun0())
  println(javaVersion.apply())
  println(anonfun0.apply())

  val l:List[String] = Nil
  l.::("Abc") // ==> "Abc" :: l

}