package classes

object P1 extends App {
 new Person1("aamir", "", "wani")
}

/**
  * Anything defined in class body becomes part of primary constructor
  * */
class Person1(firstName:String, middleName:String, lastName:String){
  println("Statement 1")

  def fullName: String = firstName + middleName + lastName
   println(fullName)
  println("Statement 2")

  if (middleName.trim.length ==0)
    println("Middle Name is empty.")

}