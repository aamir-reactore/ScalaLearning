/**Partial Functions
  * Checks if a value is contained in the function's domain.
  **/

val divide = new PartialFunction[Int, Int] {
  def apply(x: Int) = 42 / x
  def isDefinedAt(x: Int) = x != 0
}
val bol = divide.isDefinedAt(0)
val ap = divide.apply(1)
divide(2) // calls apply directly
//divide(0), java.lang.ArithmeticException: / by zero

val divide2: PartialFunction[Int, Int] = {
  case d: Int if d != 0 => 42 / d
  case _ => 0 // if this case not defined then scala.MatchError for divide2(0)
}
divide2(2)
divide2(0)

/**partial functions can be chained together **/
val convert1to5 = new PartialFunction[Int,String] {
  val arr = Array("one","two","three","four","five")
  def apply(n:Int) = arr(n - 1)
   def isDefinedAt(x: Int): Boolean = x > 0 && x < 6
}
val convert6to10 = new PartialFunction[Int,String] {
  val arr = Array("six","seven","eight","nine","ten")
  def apply(n:Int) = arr(n - 6)
  override def isDefinedAt(x: Int): Boolean = x > 5 && x < 11
}
val pf1 = convert1to5 orElse convert6to10
//pf1(12)  ArrayIndexOutOfBoundsException,coz directly calls apply method.
pf1(8)
pf1(4)

/** collection api using partial functions
  * Ref : https://alvinalexander.com/scala/how-to-define-use-partial-functions-in-scala-syntax-examples
**/
//List(0,1,2) map { divide } RTE on 0

List(0,1,2) collect { divide }

List(42, "cat") collect { case i: Int => i + 1 }

val sample = 1 to 5
val isEven:PartialFunction[Int,String] = {
  case x if x % 2 == 0 => s"$x is even"
}
val isOdd:PartialFunction[Int,String] = {
  case x if x % 2 != 0 => s"$x is odd"
}
sample.collect { isEven }
// sample.map { isEven } scala.MatchError, so alternative below
sample map {isEven orElse isOdd}


