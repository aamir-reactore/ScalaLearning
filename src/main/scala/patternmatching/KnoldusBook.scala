package patternmatching

import scala.collection.immutable

object Program1 extends App {

  //constant matching
  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")
  for (city <- cities) {
    city match {
      case "Washington" => println(s"washington is capital of USA")
      case _            => println("It is a metropolitan city")
    }
  }
}

object Program2 extends App {
  //matching on types

  val typeList: immutable.Seq[Any] = List('a', "Washington", 8.90, 5)
  for (element <- typeList) {
    element match {
      case i: Int    => println(s"got an integer: $i")
      case i: Double => println(s"got an double: $i")
      case i: String => println(s"got an string: $i")
      case _         => println(s"got something else")
    }
  }
}

object Program3 extends App {
  //matching on variables

  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")
  for (city <- cities) {
    city match {
      case "Charlotte" => println("Charlotte is in NC")
      case otherCity   => println(s"$otherCity is a metropolitan city")
    }
  }
}

object Program4 extends App {
  //matching on sequences

  val cities1 = List("Washington", "Los Angeles", "New York", "Charlotte")
  val cities2 = List("Colorado", "Los Angeles", "New York", "Charlotte")

  cities1 match {
    case List("Washington", _*) => println("It starts with Washington")
    case _                      => println("It doesn't starts with Washington")
  }

  cities2 match {
    case List("Washington", _*) => println("It starts with Washington")
    case _                      => println("It doesn't starts with Washington")
  }


  cities1 match {
    case h :: _ => println(s"$h is the capital of USA")
    case Nil    => println("Forgotten city")
  }

  val emptyCity = List.empty

  emptyCity match {
    case h :: _ => println(s"$h is the capital of USA")
    case Nil    => println("Forgotten city")
  }

  def listToString(cities: List[String]): String = cities match {
    case h :: tail => h + " " + listToString(tail)
    case Nil       => ""
  }

  println("=============Recursive print===============")
  val res = listToString(cities1)
  println(res)
}

object Program5 extends App {
  //matching on tuples and guard condition

  val tupleA: (String, String) = ("Hello", "Everyone")
  val tupleB: (String, String) = ("Learn", "Scala")

  for (tuple <- List(tupleA, tupleB)) {
    tuple match {
      case (firstMsg, secondMsg) if firstMsg == "Hello" => println(s"$firstMsg and $secondMsg is a greeting message")
      case (firstMsg, secondMsg)                        => println(s"$firstMsg and $secondMsg is not a greeting message")
    }
  }

}

object Program6 extends App {

  //matching on case classes

  case class Person(name: String, age: Int)

  val p1 = Person("Rick", 24)
  val p2 = Person("James", 42)
  val p3 = Person("Simon", 20)


  for (person <- List(p1, p2, p3)) {
    person match {
      case Person(name, age) if age > 30 => println(s"$name has $age greater than 30")
      case Person(name, age)             => println(s"$name is underage, with age:$age")
    }
  }

}