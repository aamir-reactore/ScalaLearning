import org.apache.spark.graphx.{GraphLoader, VertexId}
import org.apache.spark.sql.SparkSession

object llll extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext


  val graph1 = GraphLoader.edgeListFile(spark.sparkContext, "E:\\code\\Cit-HepTh.txt")
  val res: (VertexId, Int) = graph1.inDegrees.reduce((a, b) => if (a._2 > b._2) a else b)

  //graph1.edges.collect().take(10).foreach(println)

  // println("=======")
  //graph1.vertices.collect().take(10).foreach(println)
  graph1.triplets.collect().take(5).foreach(println)
  println("------")
  graph1.triplets.map(x => (x.toTuple._1._1, x.toTuple._2._1)).collect().take(5).foreach(println)
  // graphTriplets.sortBy(_.attr, ascending = false).map(triplet =>
  //"Distance " + triplet.attr + " from " + triplet.srcAttr + " to " + triplet.dstAttr + ".").collect.foreach(println)

  val res1 = graph1.triplets.map(x => (x.toTuple._1._1, x.toTuple._2._1)).collect().groupBy(_._1) map { case (x, y) =>
    (x.toLong, y.map(_._2).toList)
    //}
    // r.take(10).foreach(println)
    //res1.collect().take(20).foreach(println)
  }





  println(res1.take(6))

}