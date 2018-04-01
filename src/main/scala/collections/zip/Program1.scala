package collections.zip

object PZipping extends App {

  //using collect to find index of b.a
  val l = List("Abc", "a", "bbc", "qq", "a")
  val res1 = l.zipWithIndex.filter(_._1 == "a").map(_._2)
  val res2 = l.zipWithIndex.map { case ("a", index) =>
    index
  } //scala.MatchError

  val res3 = l.zipWithIndex.collect {case ("a", index) =>
    index
  }

  println(res1)
  println(res3)
}