val str = "Hello"
str.isInstanceOf[String]

class Person

case object P

val obj = new Person
obj.isInstanceOf[Serializable]
P.isInstanceOf[Serializable]


trait A {
  def display() {
    println("From A.display")
  }
}

trait B extends A {
  override def display() {
    println("From B.display")
  }
}

trait C extends A {
  override def display() {
    println("From C.display")
  }
}

class D extends B with C {}

object ScalaDiamonProblemTest extends App {
  val d = new D
  println(s">>>>>>>>>${d display}")
}

/*
class Reactore(fn: String, mn: String, ln: String) {
  def this(fn: String, ln: String) {
    this(fn, "Ahmad", ln)
  }
}

object Reactore {
  def apply(fn: String, mn: String, ln: String) = {
    new Reactore(fn, mn, ln)
  }

  def apply(fn: String, ln: String) = {
    new Reactore(fn, "Ahmad", ln)
  }
}

val obj1 = new Reactore("arif", "ahmad", "bhat")
val obj2 = new Reactore("arif", "bhat")

val obj3 = Reactore("arif", "ahmad", "bhat")
val obj4 = Reactore("arif", "bhat")

val x = null
val y: String = x
val z: Int = null //Throws a type mismatch error*/




















