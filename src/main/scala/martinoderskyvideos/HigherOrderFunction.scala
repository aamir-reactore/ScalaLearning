package martinoderskyvideos

object HigherOrderFunctionExample extends App {

  def sum(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }

  println(s"sum from 1 to 5 is ${sum(x => x, 1, 5)}")
  println(s"cube from 1 to 5 is ${sum(x => x * x * x, 1, 5)}")

}