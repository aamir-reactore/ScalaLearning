class A {
  def a = ???
  trait A {

  }
  class e {

  }
  object jj {

  }
}

//CTE
//called constructor definition must precede calling constructor definition
/*
class AB (a: String) {

  def this(a:String, b:String, c:String) {
    this(a,b)
  }
  def this(a: String, b: String) {
    this("")
  }
}*/

//correct way

class AB (a: String) {
  def this(a: String, b: String) {
    this("")
  }
  def this(a:String, b:String, c:String) {
    this(a,b)
  }

}

class CompanionTest(a:String) {

}

object CompanionTest {
  def apply(a: String): CompanionTest = new CompanionTest(a)
}

object CompanionTestMain extends App {
  val o1 = new CompanionTest("test1")
  val o2 = CompanionTest("test2")
  val o3 = CompanionTest.apply("test2")
}