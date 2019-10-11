package functions

object KnoldusBookExamples1 extends App {

  val f: Int => Int = (x: Int) => x + 2
  println(f)
  println( f.toString() )
  println(f.apply(6))
  println(f(6)) //equivalent to apply call
}