//private primary constructor
class Person private (name: String)

//new Person("aamir") inaccessible constructor

object Person {
  def apply(name: String) = new Person(name)
}
val per = Person("aamir")
//per.name object private
//per.name, works if val name:String
def add(a:Int, b:Int) : Int = ???
add(10,20)