package functions

object Main extends App {
  println(s"system's java version: ${sys.props("java.version")}")
  val javaVersion = () => sys.props("java.version")

  val anonfun0 = new Function0[String] {
    def apply(): String = sys.props("java.version")
  }
   assert(javaVersion() == anonfun0())
   //assert(javaVersion() == "") //"main" java.lang.AssertionError: assertion failed

  val anonfun0SyntacticSugar = new (() => String) {
    def apply(): String = sys.props("java.version")
  }
}

object Program1 extends App {

  val f1: () => Unit = () => {
    println("I won't return shit")
  }
  val f2: () => Unit = () => {
    println("same here bruh!")
  }

  val lb = new scala.collection.mutable.ListBuffer[() => Unit]

  def method(body: => Unit) ={
    lb += (() => body)
  }
   method(f2)
  for(entry <- lb) println( entry )

}

