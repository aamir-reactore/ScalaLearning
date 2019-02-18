package devinsideyou.higherorderfunctions

object Program1 extends App {
  println("-" * 500)

  def plusFive(input: Int) = input + 5

  def dividedByTwo(input: Int) = input / 2

  def higherOrderedRendered(functionName: String, argument: Int, f: Int => Int) =
    lowerOrderedRendered(functionName, argument, f(argument))

  val renderedPlusFiveOld =
    s"Function: plusFive\t\tArgument: ${10}\tResult: ${plusFive(10)}"
  val renderedDividedByTwoOld =
    s"Function: dividedByTwo\tArgument: ${20}\tResult: ${dividedByTwo(20)}"

  def lowerOrderedRendered(functionName: String, argument: Int, result: Int) =
    s"Function: $functionName\tArgument: $argument\tResult: $result"

  def show(functionName: String, argument: Int, f: Int => Int) =
    println(higherOrderedRendered(functionName, argument, f))

  show("+5", 10, plusFive)
  show("+5", 10, input => input + 5)
  show("+5", 10, _ + 5)
  show("/2", 20, dividedByTwo)
  show("/2", 20, input => input / 2)
  show("/2", 20, _ / 2)
  println("-" * 500)

  val multiply1: Int => Int = _ * 3
  val multiply2: Int => Int = input => input * 3

  println("*" * 500)
  show("*3", 20, _ * 3)
  show("*3", 20, multiply1)
  show("*3", 20, multiply2)
  println("*" * 500)

  println("$" * 500)

  def showRange(functionName: String, arguments: Range, f: Int => Int) = {
    arguments foreach { argument =>
      show(functionName, argument, f)
    }
  }

  def factorial(n: Int): Int = {
    def fact(n: Int, acc: Int): Int = {
      if (n == 0) acc else fact(n - 1, acc * n)
    }

    fact(n, 1)
  }

  showRange("factorial", 0 to 5, factorial)
  println("$" * 500)

  println("%" * 500)

  def higherOrderOld(): Unit = {
    def loopOld(f: () => Unit): Unit = {
      1 to 4 foreach { _ =>
        f()
      }
    }

    loopOld { () =>
      println("Hi")
    }
  }

  higherOrderOld()

  println()

  def higherOrderNew(): Unit = {
    def loopNew(f: => Unit): Unit = {
      1 to 4 foreach { _ =>
        f
      }
    }

    loopNew {
      println("Hi")
    }
  }

  higherOrderNew()
  println("%" * 500)

}