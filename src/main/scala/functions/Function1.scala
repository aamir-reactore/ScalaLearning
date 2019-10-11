package functions

object Main1 extends App {
  val succ = (x: Int) => x + 1
  val anonfun1 = new Function1[Int, Int] {
    def apply(x: Int): Int = x + 1
  }
  assert(succ(0) == anonfun1(0))

  val anonfun1SyntacticSugar = new (Int => Int) {
    def apply(x: Int): Int = x + 1
  }

  assert(anonfun1SyntacticSugar(0) == anonfun1(0))

}