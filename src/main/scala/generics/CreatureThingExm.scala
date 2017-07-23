package generics

class Thing
class Creature extends Thing
class Human extends Creature
class Dog extends Creature

class Home[T >: Creature] {
  def process(value: T): String = {
    "processed value"
  }
}

object CreatureExample extends App {

   val thing1 = new Home[Thing]
   //val dog1 = new Home[Dog] //CTE
  val c1 = new Home[Creature]
  //val d1 = new Home[Dog] CTE

  thing1.process(new Thing) // T must be Thing in process method
  thing1.process(new Creature) // creature is a thing as it extends it
  thing1.process(new Human) // human is a thing as it extends Creature that extends Thing
}

