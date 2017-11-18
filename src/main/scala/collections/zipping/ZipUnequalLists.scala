package collections.zipping

object UnequalList extends App {

  val nameList = List("aamir","obaid","muhammad","rafan")
  val likeList = List("mangoes","oranges","pears")

  val res = nameList zipAll(likeList, "", "")
  println("Result list is " + res)
  println("list size is"+res.size)
  println("Is last element first part empty " + (res(3)._1).isEmpty)
  println("Is last element second part empty " + (res(3)._2).isEmpty)
}