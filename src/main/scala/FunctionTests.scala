object FunctionTests extends App {

  val f = () => 2 + 1
  println(f)
  val x: () => Int = f
  println(f())

}