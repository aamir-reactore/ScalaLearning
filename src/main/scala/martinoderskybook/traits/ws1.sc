trait Print {
  def print()
}

abstract class PrintA4 {
  def printA4()
}

class A6 extends PrintA4 with Print {
  def print() {
    // Trait print
    println("print sheet")
  }

  def printA4() {
    // Abstract class printA4
    println("Print A4 Sheet")
  }
}

var a = new A6()
a.print()
a.printA4()

trait Print1 {
  def print()
}

abstract class PrintA41 {
  def printA4()
}

class A61 extends PrintA41 {
  def print() {
    // Trait print
    println("print sheet")
  }

  def printA4() {
    // Abstract class printA4
    println("Print A4 Sheet")
  }
}
println(">>>>>>>>>>>>>>>>>>>>>>>")
var a1 = new A61() with Print1 // You can also extend trait during object creation
a1.print()
a1.printA4()

trait PizzaTopping {
val n = 10
}
class x(val m:Int,override val n:Int) extends PizzaTopping {

}

trait Pet {
  def sound : String = "meow"
}
class XX
trait Y extends XX
class A
class B extends Y
class Starshipp
trait WarpCore {
  this: Starshipp =>
}
//class Enterprise extends WarpCore CTE
  class Enterprise extends Starshipp with WarpCore

class DavidBanner
trait Angry {
  println("You won't like me ...")
}

 val hulk = new DavidBanner with Angry
