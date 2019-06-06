
package graphx

import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Program1 extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val vertices: Array[(VertexId, String)] = Array((1L, "SFO"), (2L, "ORD"), (3L, "DFW"))
  val vRDD: RDD[(VertexId, String)] = sc.parallelize(vertices)

  val res1 = vRDD.take(1).mkString(",")
  println("res1 ==> " + res1)
  vRDD.collect().foreach(println)

  // Defining a default vertex called nowhere
  val nowhere = "nowhere"

  val edges: Array[Edge[PartitionID]] = Array(Edge(1L, 2L, 1800), Edge(2L, 3L, 800), Edge(1L, 3L, 1400))
  val eRDD: RDD[Edge[PartitionID]] = sc.parallelize(edges)

  val res2 = eRDD.take(2).mkString(",")
  println("res2 ==>" + res2)

  val graph: Graph[String, PartitionID] = Graph(vRDD, eRDD, nowhere)

  println("======degrees start========")
  val inD: VertexRDD[Int] = graph.inDegrees
  val inCollect: Array[(VertexId, PartitionID)] = graph.inDegrees.collect()
  graph.inDegrees.collect().foreach(println)
  graph.outDegrees.collect().foreach(println)
  println("======degrees end========")
  val x: VertexRDD[String] = graph.vertices
  val graphVertices: Array[(VertexId, String)] = graph.vertices.collect()
  println("==printing graph vertexes===")
  graphVertices.foreach(println)

  val graphEdges: Array[Edge[PartitionID]] = graph.edges.collect()
  val y: EdgeRDD[PartitionID] = graph.edges
  println("==printing graph edges===")
  graphEdges.foreach(println)

  val numVer = graph.numVertices
  val numEdges = graph.numEdges
  println("total vertices ==>" + numVer)
  println("total edges ==>" + numEdges)

  //filter routes > 1000
  val filterRoutes: RDD[Edge[PartitionID]] = graph.edges.filter {
    case Edge(src, dest, prop) => prop > 1000
  }
  println("===printing routes > 10000=======")
  filterRoutes.collect().foreach(println)

  //edge triplets
  val graphTriplets: RDD[EdgeTriplet[String, PartitionID]] = graph.triplets
  println("====graph triplets=======")
  graphTriplets.collect.foreach(println)

  //sort and print out longest routes desc
  graphTriplets.sortBy(_.attr, ascending = false).map(triplet =>
    "Distance " + triplet.attr + " from " + triplet.srcAttr + " to " + triplet.dstAttr + ".").collect.foreach(println)

}