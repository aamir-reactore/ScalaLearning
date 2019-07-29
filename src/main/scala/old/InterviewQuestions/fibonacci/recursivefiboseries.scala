package old.InterviewQuestions.fibonacci

object P1 extends App {

  println("Enter a limit")
  val limit: Int = scala.io.StdIn.readInt()

  def fibo(n: Int): Int = {
    if (n == 1 || n == 2) 1 else {
      fibo(n - 1) + fibo(n - 2)
    }
  }

  (1 to limit) foreach { x =>
    val fiboNum = fibo(x)
    println(fiboNum)
  }
}
