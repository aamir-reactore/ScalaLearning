package martinoderskyvideos

object HigherOrderFunctionExample1 extends App {

  def sum(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc else loop(a + 1, f(a) + acc)
    }

    loop(a, 0)
  }

  println(s"sum from 1 to 5 is ${sum(x => x, 1, 5)}")
  println(s"cube from 1 to 5 is ${sum(x => x * x * x, 1, 5)}")

}

object HigherOrderFunctionExample2 extends App {
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int = {
      if (a > b) 0 else f(a) + sumF(a + 1, b)
    }
    sumF
  }

  val res: Int = sum(x => x * x * x)(1, 5)
  println(res)
}

object HigherOrderFunctionExample3 extends App {

  def sum(f: Int => Int)(a: Int, b: Int): Int = { // here type of sum is (Int => Int) => (Int, Int) => Int
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }

  val res: Int = sum(x => x * x * x)(1, 5)
  println(res)

}

//function types associate to the right i.e Int => Int => Int equals Int => (Int => Int )