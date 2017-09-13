package applymethods.Program1

/**
  * Created by aamir on 13/9/17.
  *
  * Useful Resource:- https://blog.matthewrathbone.com/2017/03/06/scala-object-apply-functions.html
  */
object ApplyTest1 extends App {

  class MyAddser(x: Int) {
    def apply(y: Int): Int = x + y
  }

  val adder = new MyAdder(2)
  val result = adder(4) //equivalent to x.apply(4)
  println(result)
}

object ApplyTest2 extends App {

  class Greeter1(var message: String) {
    println("A greeter-1 is being instantiated with message " + message)
  }

  class Greeter2 {
    def apply(message: String): Unit = {
      println("A greeter-2 is being instantiated with message " + message)
    }
  }
  val g1: Greeter1 = new Greeter1("hello")
  val g2: Greeter2 = new Greeter2()
  g2("world")
}
object ApplyTest3 extends App {
  object Greet {
    def apply(name: String): String = {
      "Hello %s".format(name)
    }
  }
  println( Greet.apply("world") )
  println( Greet("world") )
}

object ApplyTest4 extends App {
  case class Person(name: String, age: Integer, favColor: String)
  println(Person("aamir",28,"Dark Blue"))
}