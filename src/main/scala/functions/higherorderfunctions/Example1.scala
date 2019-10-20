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

object Example3 extends App {
  val marks = Seq(100,20,4)

  val doubleMarks = (x: Int) => x * 2
  val newMarks1 = marks.map(doubleMarks)
  val newMarks2 = marks.map(_ * 2)

  println(newMarks1)
  println(newMarks2)
}


object AppraisalsRedundant {

  def smallIncrement(salaries: List[Double]): List[Double] = salaries.map(_ * 1.1)
  def greatIncrement(salaries: List[Double]): List[Double] = salaries.map(_ * 3.2)
  def bestIncrement(salaries: List[Double]): List[Double] = salaries.map(_ * 5.5)

}

object Appraisals extends App {

  private def promotion(salaries: List[Double], promotionFunction: Double => Double): List[Double] = {
    salaries.map(promotionFunction)
  }

  def smallIncrement(salaries: List[Double]): List[Double] = promotion(salaries, _ * 1.1)
  def smallIncrement1(salaries: List[Double]): List[Double] = promotion(salaries, (_:Double) * 1.9)
  def greatIncrement(salaries: List[Double]): List[Double] = promotion(salaries,salary => salary * 3.2)
  def bestIncrement(salaries: List[Double]): List[Double] = promotion(salaries, (salary:Double) => salary * 5.5)

  val salaries : List[Double] = List(2, 4, 5)

  println(smallIncrement(salaries))
  println(smallIncrement1(salaries))
  println(greatIncrement(salaries))
  println(bestIncrement(salaries))
}

object ReturningFunctionSoHigherOrderToo extends App {

   def greeting1(language: String) = (greet: String) => {
     language match {
       case "english" => s"hello $greet"
       case "spanish" => s"buenos dias $greet"
       case "french" => s"bonjour $greet"
     }
   }

  def greeting2(language: String) (greet: String) = {
    language match {
      case "english" => s"hello $greet"
      case "spanish" => s"buenos dias $greet"
      case "french" => s"bonjour $greet"
    }
  }

  println(greeting1("english")("aamir"))
  println(greeting2("spanish")("aamir"))
}