object GroupingClusterUser extends App {

  val distinctClusterIds = List("112s","112l","112z","112x","112d","112e", "112sss","112lfsdf","112333z","1sss12x","ssss112d","dsfdf112e")
  val userList = List((1,"aamir"), (2,"aamir"))
  val groupSize = Math.ceil(distinctClusterIds.length.toDouble / userList.size.toDouble).toInt
  val groupedResult= if (groupSize <= 0) List.empty else distinctClusterIds.grouped(groupSize).toList

  println(groupedResult)
 val zippedCluster = groupedResult zip userList
  println(zippedCluster)
}