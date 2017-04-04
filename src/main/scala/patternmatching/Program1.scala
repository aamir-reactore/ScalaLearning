package patternmatching

object P1 extends App {

  //why to use @
  case class Person(name: String, age: Int) {}

  val b = Person("Kevin", 100)
  val bill = Person("bill", 40)

  val res1 = b match {
    case Person(_, age) => age
    case _ => 10
  }
  val res2 = b match {
    case p @ Person(_, age) if p == bill => age
    case _ => 10
  }

  println("res1 = " + res1)
  println("res2 = " + res2)
}