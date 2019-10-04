package forcomprehensions

import scala.collection.immutable

object Program1 extends App {
  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")

  for (city <- cities
       if city != "Washington"
  ) println(city)
}

object Program2 extends App {
  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")

  val result: Seq[String] = for (city <- cities
                                 if city != "Washington"
  ) yield city
  result foreach { x => print(x + " ") }
}

object Program3 extends App {
  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")

  val result: Seq[String] = for {city <- cities
                                 upperCaseCity = city.toUpperCase
  } yield upperCaseCity
  result foreach { x => print(x + " ") }
}

object Program4 extends App {
  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")

  val result: Seq[String] = for (city <- cities)
    yield s"$city"
  result foreach { x => print(x + " ") }
}

object Program5 extends App {
  val list1 = List(1, 2, 3, 4)
  val list2 = List(1, 2, 3, 4)

  val result: immutable.Seq[Int] = for {
    x <- list1
    y <- list2
  } yield x + y

  result foreach { x => print(x + " ") }

  println()

  val resultx = list1.flatMap(x => list2.map(y => x + y))
  resultx foreach { x => print(x + " ") }

}

trait Helper extends DelayedInit {
  def delayedInit(body: => Unit) = {
    println("dummy text, printed before initialization of C")
    body // evaluates the initialization code of C
  }
}

class C extends Helper {
  println("this is the initialization code of C")
}

object Test extends App {
  val c = new C
}