val nameList = List("aamir","obaid","muhammad","rafan")
val likeList = List("mangoes","oranges","pears")

val res = nameList zipAll(likeList, "", "")
res.size
res(3)._1.isEmpty
res(3)._2.isEmpty

//List().init//.lastOption.getOrElse(throw new Exception("init list.empty"))


val t2:PartialFunction[AnyVal,String] = {
  case i:Int => s"int value $i"
  case i:Double => s"int value $i"
}
def t3:AnyVal => String = {
  case i:Int => s"int value $i"
  case i:Double => s"int value $i"
}





