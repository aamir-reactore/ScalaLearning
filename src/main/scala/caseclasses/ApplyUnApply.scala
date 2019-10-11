package caseclasses

object CalculatorApplyUnApply extends App {

  def apply(name: String, calcType: String): String = s"$calcType--$name"

  def unapply(calc: String): Option[String] = {
    val stringArray: Array[String] = calc.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }
  val calc: String = CalculatorApplyUnApply("casio", "simple")
  val CalculatorApplyUnApply(x) = calc
  val xx: String = calc

  calc match {
    case CalculatorApplyUnApply(calcType) => println(calcType)
    case x: String => println(x)
    case _                                => println("Could not extract a Calculator type")
  }
}

class TestApplyUnApply private(val name: String, val age: Int) {
  def getAgeAndName: String = s"Age: $age, name: $name"
}

object TestApplyUnApply {
  def apply(name: String, age: Int): TestApplyUnApply = {
    new TestApplyUnApply(name, age)
  }

  def unapply(arg: TestApplyUnApply): Option[(String, Int)] = {
    if (arg.name != "") Some(arg.name, arg.age) else None
  }
}

object TestApplyUnApplyMain1 extends App {
  //val s = new TestApplyUnApply("", 1) //No constructor accessible from here
  val obj: TestApplyUnApply = TestApplyUnApply("Sam", 20) //just assigning ref

  val x: TestApplyUnApply = obj //just assigning ref
  x.age
  x.name
  x.getAgeAndName

  val TestApplyUnApply(y) = obj //de-constructing
  y._1
  y._2


  //println(obj.getAgeAndName)

  obj match {
      //first one gets matched
    case TestApplyUnApply(objRef) => {
      println(s"Name: ${objRef._1}, age: ${objRef._2}")
    }
    case TestApplyUnApply(name, age) => {
      println(s"name: $name, age: $age")
    }

    case _                     => println("No object found")
  }
  //val ageName = TestApplyUnApply(obj), Error
  val ageName: Option[(String, Int)] = TestApplyUnApply.unapply(obj)
}

 object AppLTest {
   def unapply(arg: TestApplyUnApply): Option[(String, Int)] = {
     if (arg.name != "") Some(arg.name, arg.age) else None
   }
 }

object TestApplyUnApplyMain2 extends App {
  //val s = new TestApplyUnApply("", 1) //No constructor accessible from here
  val obj: TestApplyUnApply = TestApplyUnApply("Sam", 20)
  val TestApplyUnApply(name, age) = obj
  val AppLTest(name1, age1) = obj


  println(obj.getAgeAndName)

  obj match {
    case TestApplyUnApply(objRef) => println(s"Name: ${objRef._1}, age: ${objRef._2}")
    case _                     => println("No object found")
  }
  //val ageName = TestApplyUnApply(obj), Error
  val ageName: Option[(String, Int)] = TestApplyUnApply.unapply(obj)
}

