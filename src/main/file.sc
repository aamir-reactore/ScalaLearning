val nameList = List("aamir","obaid","muhammad","rafan")
val likeList = List("mangoes","oranges","pears")

val res = nameList zipAll(likeList, "", "")
res.size
(res(3)._1).isEmpty
(res(3)._2).isEmpty

List().init.lastOption.getOrElse(throw new Exception("init list.empty"))


