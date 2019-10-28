package functions

object SyntacticSugar1 extends App {

  def method1(f: => String) = {
    println(f)
  }

  val f1 = () => "Syntactic Sugar Yum, Plz add some more sugar"
  val f2 = (x: Int) => s"Syntactic Sugar Yum, Plz add some more sugar $x times"
  method1(f1())
  method1(f2(10))
}