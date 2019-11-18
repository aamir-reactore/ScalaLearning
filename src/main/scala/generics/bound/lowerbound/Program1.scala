package generics.bound.lowerbound

//https://www.journaldev.com/9609/scala-typebounds-upper-lower-and-view-bounds
object Program1 extends App {

  abstract  class Animal {
    def name:String
  }
  class Dog extends Animal {
    def name = "Labradog"
  }
  class Puppy extends Dog {
    override def name = "Nipatoucher a lintini"
  }

  class AnimalCarer {

    def display[T >: Puppy](x: T) = {
      println(x)
    }
  }

  val a = new Animal {
    def name = "abstract one"
  }
  val d = new Dog
  val p = new Puppy

  class SomeSubTypeOfAnimal extends Animal {
    override def name: String = "some sub class"
  }
  val someSubClassAnimal = new SomeSubTypeOfAnimal()

  class SubClassOfPuppy extends Puppy {
    override def name = "subclassofputty"
  }
  val subClassPuppy = new SubClassOfPuppy()

  new AnimalCarer().display(d) //dog, puppys superclass
  new AnimalCarer().display(p) // puppy itself
  new AnimalCarer().display(a) // animal superclass of Dog
  new AnimalCarer().display(someSubClassAnimal) // its also an animal
  new AnimalCarer().display(subClassPuppy) // becoz its a puppy

}
