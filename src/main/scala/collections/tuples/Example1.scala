package collections.tuples

object Example1 extends App {

  val t1: (Int, String) = (1, "Aamir")

  val t2: (String, Int) = t1.swap

  val t3: (Int, String) = new Tuple2[Int, String](1, "Jammy") // through tuple22
}

object ExampleTuplePatternMatch extends App {

  val student = (1, "Aamir", "Bangalore")

  val (id, name, address) = student

  val tupleList = List((1, "Aamir"),(2, "Mark"), (3, "Jimmy"), (4, "Zach"))

  tupleList foreach { case(_, someName) =>
    println(someName)
  }

}