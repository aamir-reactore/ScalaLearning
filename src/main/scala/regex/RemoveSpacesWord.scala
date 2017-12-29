package regex

object SpaceRemoveTest extends App {
  val str = "Live Mine"
  println(str.replaceAll("\\s+",""))
}