package functions.higherorderfunctions

object Example1 extends App {

    println(apply(format, 32))


  // A higher order function
  def apply(x: Double => String, y: Double) = x(y)

  def format[R](z: R) = "{" + z.toString + "}"

}

object Example2 extends App {
    val num = List(7, 8, 9)

    def multiplyValue: Int => Int = (y: Int) => y * 3

    // Creating a higher order function
    // that is assigned to the variable
    val result = num.map(y => multiplyValue(y))

    println("Multiplied List is: " + result)
}