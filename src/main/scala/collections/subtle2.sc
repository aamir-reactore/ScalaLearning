val values: List[Int] = List(1, 2, 3, 4, 5)
val result: Seq[List[Int]] = values.grouped(2).toSeq
result.foreach(println)

case class Programmer(id:Long,name:String)
val p1 = Programmer(1,"Josh")
val p2 = Programmer(2,"Jammie")
val p3 = Programmer(3,"Dustin")
val p4 = Programmer(4,"Eigor")

val t1 = (10,List(p1,p2))
val t2 = (20,List(p3,p4))

val newWay = Map(List(t1,t2):_*)

val map: Map[Int, List[Programmer]] = List(t1,t2).toMap

val resx: Option[List[Programmer]] = map.get(10)
val resy: List[List[Programmer]] = map.get(10).toList
val resz: List[Programmer] = map.get(10).toList.flatten


val resx1: Option[List[Programmer]] = map.get(90)
val resy1: List[List[Programmer]] = map.get(90).toList
val resz1: List[Programmer] = map.get(90).toList.flatten

val x = Some(1)
x.toList

val y = Some(List(1))
y.toList