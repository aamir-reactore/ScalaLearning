package devinsideyou.traits

class Animal
trait Pet {
  def isAllowedToSleepInBed: Boolean
  def disallowedToSleepInBed = !isAllowedToSleepInBed
}
class Cat extends Animal with Pet {
  val isAllowedToSleepInBed = true
}
class Turtle extends Animal with Pet {
  def isAllowedToSleepInBed: Boolean = false
}

object Test1 extends App {

  def show(pet: Pet) = println(pet.disallowedToSleepInBed)
  show(new Cat)
  show(new Turtle)
}