package closures
object DefaultParameterValue1 extends App {

  def printName(name: String = "aamir",age:Int = 24) = {
    println(s"name is $name, age is $age")
  }

  printName()
  printName("obaid")
  printName(age = 28)
}