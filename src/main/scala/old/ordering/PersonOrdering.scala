package ordering


object PersonOrdering1 extends App {

  import scala.util.Sorting

  case class Person1(name: String, age: Int)

  val people = Array(Person1("bob", 30), Person1("ann", 32), Person1("carl", 19))

  object AgeOrdering extends Ordering[Person1] {
    def compare(a: Person1, b: Person1): Int = a.name compare b.name
  }

  Sorting.quickSort(people)(AgeOrdering)
  people.foreach(x => println(x.name))

}

object PersonOrdering2 extends App {

  implicit object PersonOrdering extends Ordering[Person2] { //implicit cannot be applied on top level objects
  def compare(p1:Person2, p2:Person2) = p1.age.compare(p2.age)
  }

  case class Person2(name: String, age: Int)
  val persons = Seq(
    Person2("Young", 17),
    Person2("MidLifeCrisis", 40),
    Person2("Wise", 99)
  )

  println(persons.minBy(_.age))
  println(persons.minBy(_.name))
  println(persons.max)

}

