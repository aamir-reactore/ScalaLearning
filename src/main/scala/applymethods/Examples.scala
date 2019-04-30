package applymethods

/**
  * Created by aamir on 13/9/17.
  * Useful Resource:- https://blog.matthewrathbone.com/2017/03/06/scala-object-apply-functions.html
  */
object ApplyTest1 extends App {

  class MyAdder(x: Int) {
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
    def apply(name: String): String = s"Hello $name"
  }
  println( Greet.apply("world") )
  println( Greet("world") )
}

object ApplyTest4 extends App {
  //Case class has no constructor but has b.a default apply method.
  case class Person(name: String, age: Integer, favColor: String)
  println(Person("aamir",28,"Dark Blue"))
}

object ApplyTest5 extends App {
  class Person(val name: String)
  object Person {
    def apply(name: String): Person = new Person("aamir")
  }
  val person1: Person = new Person("aamir")
  val person2: Person = Person("aamir")

}

object ApplyTest6 extends App {
//  Apply Functions are used for Anonymous Functions
  // In scala you can create an anonymous function like so:

  val func1: String => String = (x: String) => "hello %s".format(x)
  val func2: String => String = new Function1[String,String] {
    def apply(s:String) = "hello %s".format(s)
  }
  //syntactic sugar
  val func3 = new (String => String) {
    def apply(s:String) = "hello %s".format(s)
  }
  println(func1("world"))
  println(func2("world"))
  println(func2.apply("world"))
  println(func3("world"))
}
