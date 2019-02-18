
object test extends App {
  val r = "\u2192"
  val s = "expedia lodging partner services dominican" + r + "dop"
  println(s)
  val tmpStr = if (s.contains("expedia lodging partner services dominican")) "expedia lodging partner services dominican dop" else s

  println(tmpStr)
}
