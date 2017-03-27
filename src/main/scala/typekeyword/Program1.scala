package typekeyword

object TypeP1 extends App {

  //A sealed trait or class can be extended only in the same file as its declaration.
  // type aliases
  type myType = Map[Int, String]
  type IntPair = (Int, Int)
  //now use it anywhere
  def method1(abc:Int):myType = Map(1 -> "abc")
}

//Scala also allows a class to have type members.
trait BaseTypeP1 {
  type T
  def method: T
}

class Implementation extends BaseTypeP1 {
  type T = Int
  def method: T = 42
}
