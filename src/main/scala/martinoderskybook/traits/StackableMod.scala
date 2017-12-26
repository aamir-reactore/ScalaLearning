package martinoderskybook.traits

/**
  * References:
  * http://blog.ryannedolan.info/2014/03/scala-pattern-stackable-traits.html
  */
/**
  * Points:
  * In Stackable trait pattern, super means the previous trait in linearization order
  * In STP, super is dynamically influenced on how the trait is mixed in
  */
abstract class Bar { def bar(x: Int): Int }
class Boo extends Bar { def bar(x: Int) = x }

trait Foo1 extends Bar { abstract override def bar(x: Int):Int = x + super.bar(x) }
trait Foo2 extends Bar { abstract override def bar(x: Int):Int = x * super.bar(x) }

object StackableMod1 extends App {
  val x1 = new Boo with Foo2 with Foo1
  println(x1.bar(5))

  val x2 = new Boo with Foo1 with Foo2
  println(x2.bar(5))
}

  //Base Abstract Class Pizza
  abstract class Pizza {
    def price(): Double
    def description(): String
  }

  //We also have a class VegDelightPizza
  class VegDelightPizza extends Pizza {
    override def price: Double = 10.0
    override def description: String = {
      "Veg Delight Pizza"
    }
    def printDesc() = println (description)
    def printPrice() = println ("Price : $" + price)
  }

  //Our restricted traits for extra
  //Trait for adding Extra Mozzarella Cheese
  trait ExtraMozzarellaCheese extends VegDelightPizza{
    override def price():Double = super.price + 0.50
    override def description():String = {
      super.description + "\n + Extra Mozzarella Cheese"
    }
  }

  //Trait for adding Extra Tomato Toppings
  trait ExtraTomatoToppings extends VegDelightPizza{
    override def price():Double = super.price + 0.75
    override def description():String = {
      super.description + "\n + Extra Tomato Toppings"
    }
  }

  //Trait for adding Extra Black Olives
  trait ExtraBlackOlives extends VegDelightPizza{
    override def price():Double = super.price + 0.15
    override def description():String = {
      super.description + "\n + Extra Black Olives"
    }
  }
object PizzaStackMod extends App {

    //This is how you implement stackable traits
    var pizza1 = (new VegDelightPizza
      with ExtraMozzarellaCheese
      with ExtraBlackOlives)

    pizza1.printDesc
    pizza1.printPrice
/*    println ("####")

    //Another implementation of stackable traits
    var pizza2 = (new VegDelightPizza
      with ExtraMozzarellaCheese
      with ExtraBlackOlives
      with ExtraTomatoToppings)*/
/*
    pizza2.printDesc
    pizza2.printPrice*/
}
 class T1 {
  def method1():String = "t1"
}
trait A1 extends T1 {
  override def method1() = super.method1() + "A1"
}
trait B1 extends T1 {
  override def method1() = super.method1() + "B1"
}

class C1 extends B1 with A1 {
}
object C1Test extends App {
  println(new C1().method1())
}

class Printer {
  def print(str: String): Unit = {
    println(str)
  }
}
//abstract override traits only
trait Pretty extends Printer {
  abstract override def print(str: String): Unit = {
    super.print("pretty " + str)
  }
}
trait NewLine extends Printer {
  abstract override def print(str: String): Unit = {
    super.print(str + " *new line!*")
  }
}
trait Caps extends Printer {
  abstract override def print(str: String): Unit = {
    super.print(str.capitalize)
  }
}
object SM1 extends App {
  val printer = new Printer with Pretty with NewLine with Caps
  printer.print("hello")
}
object jj extends App {
  //legal inheritance
  class XX1
  trait Y1 extends XX1
  class A1
  class B1 extends Y1 //this is normal inheritance, works becoz no abstract override method inside Y1

  println(new B1())

  //illegal inheritance
  /*  class StarfleetComponent
    trait WarpCore extends StarfleetComponent
    class RomulanStuff
    class Warbird extends RomulanStuff with WarpCore*/

  //legal inheritance
  class StarfleetComponent1
  trait WarpCore1 extends StarfleetComponent1
  class Starship1 extends StarfleetComponent1 with WarpCore1
  /**
    *The trait and extending class should share same super class
    */
}
object ll extends App {
  class Starship2
  class SomeShip extends Starship2
  trait WarpCore2 {
    this: Starship2 =>
  }
  //class Enterprise2 extends WarpCore2
  class Enterprize3 extends SomeShip with WarpCore2
  class Enterprize4 extends Starship2 with WarpCore2
}