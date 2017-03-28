package higherorderfuns

object P1 extends App {

  //A function of 0 parameters.

    //In the following example, the definition of javaVersion is a shorthand for the anonymous class definition anonfun0:

    val javaVersion: () => String = () => sys.props("java.version")

    val anonfun0 = new Function0[String] {
      def apply(): String = sys.props("java.version")
    }
  assert(javaVersion() == anonfun0())

  val l:List[String] = Nil
   l.::("Abc")
}