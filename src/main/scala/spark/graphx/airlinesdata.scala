package spark

import org.apache.spark.graphx.{Edge, Graph, VertexId, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql._

object AirLineGraph extends App {

  def parseFlight(str: String): Option[Flight] = {
    val line: Array[String] = str.split(",")
    if (line.length == 17) {
      if (line(0).isEmpty || line(1).isEmpty || line(2).isEmpty || line(3).isEmpty || line(4).isEmpty || line(5).isEmpty ||
        line(6).isEmpty || line(7).isEmpty || line(8).isEmpty || line(9).isEmpty || line(10).isEmpty ||
        line(11).isEmpty || line(12).isEmpty || line(13).isEmpty || line(14).isEmpty ||
        line(15).isEmpty || line(16).isEmpty) {
        None
      } else {
        Some(Flight(line(0), line(1), line(2), line(3), line(4).toInt, line(5).toLong,
          line(6), line(7).toLong, line(8), line(9).toDouble, line(10).toDouble,
          line(11).toDouble, line(12).toDouble, line(13).toDouble, line(14).toDouble,
          line(15).toDouble, line(16).toInt))
      }
    } else None
  }


  case class Flight(dofM: String, dofW: String, carrier: String, tailnum: String, flnum: Int, org_id: Long,
                    origin: String, dest_id: Long, dest: String, crsdeptime: Double, deptime: Double,
                    depdelaymins: Double, crsarrtime: Double, arrtime: Double, arrdelay: Double,
                    crselapsedtime: Double, dist: Int)

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext


  val textRDD = sc.textFile("E:\\code\\SparkLearning\\airlinesdata\\airlinedata.csv")

  val flightsRDD: RDD[Flight] = textRDD.map { x =>
    parseFlight(x)
  }.filter(_.isDefined).map(_.get).cache()


  val airports: RDD[(Long, String)] = flightsRDD.map(flight => (flight.org_id, flight.origin))

  // Defining a default vertex called nowhere
  val nowhere = "nowhere"

  val routes = flightsRDD.map(flight => ((flight.org_id, flight.dest_id), flight.dist)).distinct
  routes.cache()

  // AirportID is numerical - Mapping airport ID to the 3-letter code
  val airportMap = airports.collect().toList.toMap

  // Defining the routes as edges
  val edges: RDD[Edge[Int]] = routes.map { case ((org_id, dest_id), dist) => Edge(org_id, dest_id, dist) }

  val graph = Graph(airports, edges, nowhere)
  println(s"====Number of airports====${graph.numVertices}")
  println(s"====Number of routes====${graph.numEdges}")
  graph.triplets.take(3).foreach(println)

  // Define a reduce operation to compute the highest degree vertex
  def max(a: (VertexId, Int), b:(VertexId, Int)): (VertexId, Int) = if(a._2 > b._2) a else b

  val inDegree: VertexRDD[Int] = graph.inDegrees//.reduce(max)
  val maxInDegree: (VertexId, Int) = graph.inDegrees.reduce(max)
  val maxOutDegree: (VertexId, Int) = graph.outDegrees.reduce(max)
  val maxDegrees: (VertexId, Int) = graph.degrees.reduce(max)

  println(s"===max inDegree==== ${maxInDegree}")
  println(s"===max out==== ${maxOutDegree}")
  println(s"===max degree==== ${maxDegrees}")

  val maxIncommings = graph.inDegrees.collect().sortWith(_._2 > _._2).map{x =>(airportMap(x._1), x._2)}
  println("===Airport with most incomming flights=====")
  maxIncommings.take(4).foreach(println)

  val maxOutcommings = graph.outDegrees.collect().sortWith(_._2 > _._2).map{x =>(airportMap(x._1), x._2)}
  println("===Airport with most outgoing flights=====")
  maxOutcommings.take(4).foreach(println)


}