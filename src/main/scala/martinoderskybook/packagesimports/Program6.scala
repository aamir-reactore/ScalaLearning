package martinoderskybook.packagesimports

abstract class Fruit(val name: String, val color: String)

object Fruits {

  object Apple extends Fruit("apple", "red")

  object Orange extends Fruit("orange", "orange")

  object Pear extends Fruit("pear", "green")

  val menu = List(Apple, Orange, Pear)
}

object TestImports extends App {

  import martinoderskybook.packagesimports.Fruits.Apple
  import martinoderskybook.packagesimports.Fruits._
 //can import regular and standalone objects
  def showFruit(fruit: Fruit) = {
    import fruit._
    println(s"name = $name, color = $color")
  }
}