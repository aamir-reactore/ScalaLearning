(1 until 10).view.filter(_%5==0).map(_ + 1).sum


//back

/**
  * Source
  * https://alvinalexander.com/scala/how-to-create-lazy-views-collections-scala-cookbook
  */
(1 until 10).view.force

val x = (1 to 1000).view.map { e =>
  Thread.sleep(1000)
  e * 2
}

//x.take(2).foreach(println)



/*val y = (1 to 1000).map { e =>
  Thread.sleep(10)
  e * 2
}*/

val l = List(1,2,3,4,5,6,7,8)
l.view.reverse
l.view.reverse.take(4)

println("##################################")
//The view does not copy these elements,
// it just provides b.a reference to them.
val arr = (1 to 10).toArray
val view = arr.view.slice(2, 5)
arr(2) = 42
view.foreach(println)
view(0) = 10
view(1) = 20
view(2) = 30
arr
println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
/**
  * Source:http://www.scala-lang.org/docu/files/collections-api/collections_42.html
  */
//val v = Vector((1 to 10) : _*)
val v = Vector(1 to 10 : _*)
(v.view map (_ + 1) map (_ * 2)).force
