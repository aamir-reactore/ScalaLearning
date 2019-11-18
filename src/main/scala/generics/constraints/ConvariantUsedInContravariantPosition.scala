package generics.constraints


object ConvariantUsedInContravariantPosition extends App {

  case class KashList[+T](h: T, t: KashList[T]) {
    def head: T = h

    def tail: KashList[T] = t

    def prepend[B >: T](x: B): KashList[B] = KashList(x, this)
  }

  class Animal

  class Dog extends Animal

  class Cat extends Animal

  var dogList: KashList[Dog] = KashList[Dog](new Dog(), null)
  val animalList: KashList[Animal] = dogList

  val animal = new Animal()
  val dog = new Dog()
  val cat = new Cat()

  dogList = dogList.prepend(dog)
  //dogList = dogList.prepend(animal)
  //dogList = dogList.prepend(cat)

  println(dogList)


}