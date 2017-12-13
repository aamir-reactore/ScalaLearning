import actors.erotcaer.actor.ItemGroup

val s1 = ItemGroup("s1",List("s1-v1","s1-v2"))
val s2 = ItemGroup("s2",List("s2-v1","s2-v2"))
val s11 = ItemGroup("s1",List("s1-v3","s1-v4"))

val batchSize = 4

val flattened= List(s1,s2,s11)
  .map(group => (group.groupName,group.values))
  .groupBy(_._1)
  .map(f => (f._1,f._2.flatMap(_._2)))

val refinedgroups = flattened.flatMap(group => {
  group._2.grouped(batchSize).map(newGroup => ItemGroup(group._1, newGroup))
})

refinedgroups.foreach(println)
//              .groupBy(_.)
//            .grouped(3).toList