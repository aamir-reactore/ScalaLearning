package old.cakepattern

/**
  * Ref://https://dzone.com/articles/duck-typing-scala-structural
  */
object BigDuck {
  def quack(value: String) = {
    value.toUpperCase
  }
}

object SmallDuck {
  def quack(value: String) = {
    value.toLowerCase
  }
}

object IamNotReallyADuck {
  def quack(value: String) = {
    "prrrrrp"
  }
}

object NoQuaker {

}

object ST1 extends App {

  def quacker(duck: {def quack(value: String): String}) {
    println (duck.quack("Quack"))
  }

  quacker(BigDuck)
  quacker(SmallDuck)
  quacker(IamNotReallyADuck)
  //quacker(NoQuaker) CTE

  val x = new AnyRef {
    def quack(value: String) = {
      "No type needed "+ value
    }
  }
  quacker(x)
}