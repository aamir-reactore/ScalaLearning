package old.classes

object P1 extends App {
 new Person1("aamir", "", "wani")
  val teresa = new Employee2("Teresa", Address2("Louisville", "KY"), 25)
}

/**
  * Anything defined in class body becomes part of primary constructor
  * */
class Person1(firstName:String, middleName:String, lastName:String) {
  println("Statement 1")

  def fullName: String = firstName + middleName + lastName
   println(fullName)
  println("Statement 2")

  if (middleName.trim.length ==0)
    println("Middle Name is empty.")

}

class Person2(var name:String, var address: Address2) {
  override def toString:String = if (address == null) name else s"$name @ $address"
}

class Employee2(name:String, address: Address2, var age: Int) extends Person2(name, address)
/**
  *Because Scala has already generated the getter and setter methods for the name and address
  * fields in the Person class, the solution is to declare the Employee constructor without var declarations
  */

case class Address2(city:String, state:String)

/**
  * provide default values for Scala constructor parameters
  *  this can eliminate the need for auxiliary constructors
  *  class Socket(val timeout: Int) {
         def this() = this(10000)
    override def toString = s"timeout: $timeout"
       } // we can create with 1 arg and no arg Socket.
  * */

object P2 extends App {

  val x = new Socket
  println(x.timeout)
  val y = new Socket(8000)
  val z = new Socket(timeout = 8000)
  println(y.timeout)
  val cc1 = Socket2(timeOut = 3000) //CTE Socket2(3000), even though u got default value,
  // compiler thinks u are trying to override it based on index of the variable

  val student = Student(firstName = "Foo")
}
class Socket (val timeout: Int = 10000)
case class Socket2(name:String = "my socket", timeOut:Int) //  but declare default variable at last

case class Student(firstName : String = "John", lastName : String = "Doe")


/**
  * How to use parameter names when calling a Scala method
  * This approach has the added benefit that you can place the fields in any order:
  */

object P3 extends App {

  // new Pizza.update(crustSize = 16, crustType = "Thick"), error use ()
  new Pizza().update(crustSize = 16, crustType = "Thick")

  def bools(pressure:Boolean, wind:Boolean, temperatur:Boolean) = true
  bools(true,true,true) // confusing
  bools(pressure=true,wind=true,temperatur=true) // makes sense
}

class Pizza {
  var crustSize = 12 //field since its part of primary constructor
  var crustType = "Thin" ////field since its part of primary constructor
  def update(crustSize: Int, crustType: String) {
    this.crustSize = crustSize
    this.crustType = crustType
  }
  override def toString = {
    "A %d inch %s crust pizza.".format(crustSize, crustType)
  }
}