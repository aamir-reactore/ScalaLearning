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