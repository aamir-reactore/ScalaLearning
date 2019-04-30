trait Example {
  def func: Int => Int
}
class SomeClass extends Example {
  def func: Map[Int, Int] = Map(1->2, 3->4)
}
//reason: https://stackoverflow.com/questions/48494956/scala-why-does-scala-allow-implementing-a-function-literal-with-map-list-etc