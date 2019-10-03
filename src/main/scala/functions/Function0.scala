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