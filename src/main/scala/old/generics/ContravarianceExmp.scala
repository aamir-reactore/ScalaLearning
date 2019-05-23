package generics

/**
  * Reference: https://stackoverflow.com/questions/48544153/contravariance-why-is-the-subtype
  */
trait Shape {
  def area: Double
}
class Circle(val radius:Double) extends Shape {
  override val area: Double = math.Pi * radius * radius
}

object TestContra1 extends App {

  def writeJson(circles: List[Circle], jsonWritingFunction: Circle => String):String = {
    circles.map(jsonWritingFunction).mkString("\n")
  }

  def circleWriter(circle:Circle):String = {
    s"""{ "type" : "circle writer", radius :"${circle.radius}", "area" : "${circle.area}" }"""
  }
  def shapeWriter(shape:Shape):String = {
    s"""{ "type" : "shape writer", "area" : "${shape.area}" }"""
  }

   val circleList = List(new Circle(1.1),new Circle(6.2),new Circle(3.4))

  println(writeJson(circleList,circleWriter))
  println("####################################")
  println(writeJson(circleList,shapeWriter))
  //Shape => String is b.a subtype of Circle => String
  //i.e Function[-A,+B] || Function[Shape,String] <:Function[Circle,String] as Circle <: Shape
}

/*
object TestContra2 extends App {
  class Animal 
 class Dog extends Animal 
 class Beagle extends Dog 
 def countDogsLegs(dogs: List[Dog], legCountFunction: Dog => Int): Int = {
    dogs.map(legCountFunction).sum
  }
  def countLegsOfAnyAnimal(b.a: Animal): Int = 4

  countDogsLegs(List(new Dog(),new Dog()),countLegsOfAnyAnimal)
  //Function1[Animal,Int] <: Function1[Dog,Int] since Dog <: Animal
}*/
