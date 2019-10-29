package generics.bound.upperbound

//https://www.journaldev.com/9609/scala-typebounds-upper-lower-and-view-bounds
object Program1 extends App {

  class Animal
  class Dog extends Animal
  class Puppy extends Dog

  class AnimalCarer {

    def display[T <: Dog](x: T) = {
      println(x)
    }
  }

  val a = new Animal
  val d = new Dog
  val p = new Puppy

  new AnimalCarer().display(d)
  new AnimalCarer().display(p)
  //new AnimalCarer().display(a)

}
