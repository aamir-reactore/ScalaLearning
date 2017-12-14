package xml


object xmltest extends App {
  import scala.xml._
  val xmlData = "<temp>hello</temp>"

  val xmlObj = XML.loadString(xmlData)
  println("content ===> "+xmlObj.text)
}