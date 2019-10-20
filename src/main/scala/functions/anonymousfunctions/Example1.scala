package functions.anonymousfunctions

object Example1 extends App {

     var myfc1: (String, String) => String = (str1:String, str2:String) => str1 + str2

    // An anonymous function is createdusing _ wildcard instead of variable name because str1 and str2 variable appear only once
    var myfc2: (String, String) => String = (_:String) + (_:String)

    // Here, the variable invoke like a function call
    println(myfc1("Geeks", "12Geeks"))
    println(myfc2("Geeks", "forGeeks"))

}
object Example2 extends App {

    // Creating anonymous functions without parameter
    var myFun1: () => String = () => {"Welcome to Anonymous function...!!"}
    def myFun2() = "Some String"

    println(myFun1())
    println(myFun1) // outputs <function0>

  def funct(f: => String): String = f
  def functX(f:() => String): () => String = f

   //funct(myFun1) // here parenthesis is mandatory
  println ( funct(myFun1()) )
  println ( funct(myFun2) )

    // A function which contain anonymous function as a parameter
    def myFunction(fun: (String, String) => String) = {
      fun("Dog", "Cat")
    }

    // Explicit type declaration of anonymous function in another function
    val f1: String = myFunction((str1: String, str2: String) => str1 + str2)

    // Shorthand declaration using wildcard
    val f2: String = myFunction(_ + _)
    val f3: String = myFunction((_:String) + (_:String))
    println(f1)
    println(f2)
    println(f3)

}