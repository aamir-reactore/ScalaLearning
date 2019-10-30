package generics.covariance
//https://blog.codecentric.de/en/2015/03/scala-type-system-parameterized-types-variances-part-1/
object Program2 extends App {

  abstract class Fruit { def name: String }
  class Orange extends Fruit { def name = "Orange" }
  class Apple extends Fruit { def name = "Apple" }


  class Box[+F <: Fruit](aFruit: F) {
    def fruit: F = aFruit
    def contains(aFruit: Fruit) = fruit.name == aFruit.name
  }

  var appleBox = new Box[Apple](new Apple)
  var orangeBox = new Box[Orange](new Orange)

  var box: Box[Fruit] = new Box[Apple](new Apple)
}