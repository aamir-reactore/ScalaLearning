package generics.bound.viewbounds

//https://www.journaldev.com/9609/scala-typebounds-upper-lower-and-view-bounds
object Program1 extends App {

  class Person[T <% Ordered[T]](firstName: T, lastName: T) {
    def greater: T = if (firstName > lastName) firstName else lastName
  }

  val p1 = new Person("sam", "elliot")
  val whoIsGreater = p1.greater

  println(whoIsGreater)
}

//using implicit ordering
object Program2 extends App {
  
  class Person[T](firstName: T, lastName: T)(implicit ord: Ordering[T])  {
    def greater: T = {
      val result = ord.compare(firstName, lastName)
      println(result)
      if(result < 0) firstName else lastName
    }
  }

  val p1 = new Person("sam", "elliot")
  val p2 = new Person("samuel", "zac")
  val whoIsGreater1 = p1.greater
  val whoIsGreater2 = p2.greater

  println(whoIsGreater1)
  println(whoIsGreater2)
  
}


object Program3 extends App {

  case class Person(name: String, age: Int)

   implicit object AgeOrdering extends Ordering[Person] {
    def compare(a: Person, b: Person): Int = a.age compare b.age
  }

  val people: Array[Person] = Array(Person("aa", 30), Person("bb", 32), Person("cc", 19))
  println(people.sorted.toList)

}
