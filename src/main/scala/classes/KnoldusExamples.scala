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