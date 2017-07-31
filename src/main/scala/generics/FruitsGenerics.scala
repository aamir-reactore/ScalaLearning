package generics

/**
  * https://blog.codecentric.de/en/2015/03/scala-type-system-parameterized-types-variances-part-1/
  * http://www.journaldev.com/9585/scala-variances-covariant-invariant-contravariant
  */
abstract class Fruit {
  def name:String
}
class Apple extends Fruit {
  override def name: String = "Apple"
}
class Orange extends Fruit {
  override def name: String = "Orange"
}
/*abstract class Box {
  def fruit:Fruit
  def contains(aFruit: Fruit): Boolean = fruit.name.equals(aFruit.name)
}
class OrangeBox(orange: Orange) extends Box {
  def fruit:Orange = orange
}
class AppleBox(apple: Apple) extends Box {
  def fruit:Apple = apple
}

object FruitGenericsTest1 extends App {
  val apple = new Apple
  val box:Box = new AppleBox(apple)
  if( box contains apple) {
    println(s"box contains an ${box.fruit.name}")
  }
}*/

abstract class Box[+F <: Fruit] {
  def fruit:F
  def replace[U >: F](replacement: U)
  def contains(aFruit:Fruit) = fruit.name == aFruit.name
}
class OrangeBox(orange: Orange) extends Box[Orange] {
  def fruit:Orange = orange

  override def replace[U >: Orange](replacement: U): Unit = ???
}

class AppleBox(apple: Apple) extends Box[Apple] {
  def fruit:Apple = apple

  override def replace[U >: Apple](replacement: U): Unit = ???
}

object FruitGenericsTest2 extends App {

  var fruitBox: Box[Fruit] = new AppleBox(new Apple)

  var fruit: Fruit = fruitBox.fruit
  println(fruit.name)
}

/*
class Box2[-F <: Fruit](aFruit: F) {
  def fruit:F = aFruit
  def contains(aFruit:Fruit) = fruit.name == aFruit.name
}
object FruitGenericsTest3 extends App {
  val appleBox: Box2[Apple] = new Box2[Apple](new Apple)
  val orangeBox: Box2[Orange] = new Box2[Orange](new Orange)

  // Illegal: Box[Apple] is no subtype of Box[Fruit].
  var box: Box2[Apple] = new Box2[Fruit](new Apple)
}
*/
object FruitContravariant extends App {
  abstract class Box {

    def fruit: Fruit

    def contains(apple: Apple) = fruit.name.equals(apple.name)
  }

  class AppleBox(apple: Apple) extends Box {

    def fruit: Apple = apple
    // more generic class Fruit instead of Apple: would be type safe, but isn't allowed.
    //override def contains(aFruit: Fruit) = fruit.name.equals(aFruit.name)
  }
}

abstract class Boxz[+A] {
  def foo[B >: A](b: B) // way to use covariant in method argument
}

abstract class Boxy[-A] {
  def foo[A](): A//way to use contravariant in method return type
}