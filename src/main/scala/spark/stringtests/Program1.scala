package stringtests

object StringTest1 extends App {
  val str = "99999999999"
  println(str.toLowerCase())

  var str1 = "\\xstrata asinc. as\\\"\\\"brunswick\\\"\\\"\\\"\""
  str1 = str1.replaceAll("\\\\", "").replaceAll("\"", "")
  println(str1)

}