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

  new AnimalCarer().display(d)
  new AnimalCarer().display(p)
  new AnimalCarer().display(a)

}
