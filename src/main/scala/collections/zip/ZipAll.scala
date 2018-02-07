package collections.zip


object UnequalList extends App {

  val trips = List("pt1","pt2","pt3","pt4","pt5","pt6")
  val trucks = List(1,2)

  val res: List[(Any, Int)] = trips zipAll(trucks, 0, 0)

  println(s"zipAll result  = $res")


  val unAssignedTrips = res.filter(_._2 == 0).map(_._1)

  //val assignedTrips = res.filter(_._2.nonEmpty).map(_._1)

 // println(s"assigned trips = $assignedTrips")
  //println(s"unassigned trips = $unAssignedTrips")


 // println("list size is"+res.size)
  //println("Is last element first part empty " + res(3)._1.isEmpty)
 // println("Is last element second part empty " + res(3)._2.isEmpty)
}