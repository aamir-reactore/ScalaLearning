package zettasense


import org.apache.spark.graphx.GraphLoader

object Test1 extends  App {

  // Creates a SparkSession.
  //      val spark = SparkSession
  //        .builder
  //        .appName(s"${this.getClass.getSimpleName}")
  //        .getOrCreate()
  val spark = org.apache.spark.sql.SparkSession.builder
    .master("local")
    .appName("ZettaSense")
    .getOrCreate;
  val sc = spark.sparkContext

  // $example on$
  // Load the graph as in the PageRank example
  val graph = GraphLoader.edgeListFile(sc, "C:\\Workspace\\ZettaSense_A\\data\\graphx\\followers.txt")
  // Find the connected components
  val cc = graph.connectedComponents().vertices
  val ss = graph.connectedComponents().subgraph()

  val size = cc.collect().length
  println("sizze==>" + size)
  println("cc==>" + cc.collect().mkString("\n"))

  // Join the connected components with the usernames
  val users = sc.textFile("C:\\Workspace\\ZettaSense_A\\data\\graphx\\users.txt").map { line =>
    val fields = line.split(",")
    (fields(0).toLong, fields(1))
  }
  val ccByUsername = users.join(cc).map {
    case (id, (username, cc)) => (username, cc)
  }
  // Print the result
  println("##########")
  println(ccByUsername.collect().mkString("\n"))
  // $example off$
  spark.stop()
}

object Test2 extends App {
  import org.apache.spark.graphx.GraphLoader
  val spark = org.apache.spark.sql.SparkSession.builder
    .master("local")
    .appName("ZettaSense")
    .getOrCreate;
  val sc = spark.sparkContext
  // Load the edges as a graph
  val graph = GraphLoader.edgeListFile(sc, "C:\\Workspace\\ZettaSense_A\\data\\graphx\\followers.txt")
  // Run PageRank
  val ranks = graph.pageRank(0.0001).vertices
  // Join the ranks with the usernames
  val users = sc.textFile("C:\\Workspace\\ZettaSense_A\\data\\graphx\\users.txt").map { line =>
    val fields = line.split(",")
    (fields(0).toLong, fields(1))
  }
  val ranksByUsername = users.join(ranks).map {
    case (id, (username, rank)) => (username, rank)
  }
  // Print the result
  println(ranksByUsername.collect().mkString("\n"))
}

object Test3 extends App {
  import org.apache.spark.graphx._
   val spark = org.apache.spark.sql.SparkSession.builder
    .master("local")
    .appName("ZettaSense")
    .getOrCreate;
  val sc = spark.sparkContext

  // Construct the graph in the above example
  val edges = sc.parallelize(List(
    Edge(1L, 2L, "e1"), Edge(2L, 3L, "e1"), Edge(3L, 4L, "e1")))
  val g: Graph[Int, String] = Graph.fromEdges(edges, 0)

  println("====")
     g.vertices.foreach(x => println(x + ">>"))
  //println("e ==>" + g.edges.collect().mkString)
 // println("v ==>" + g.vertices.collect().mkString)

  val sourceVertexId: VertexId = 1L // vertex a in the example
  val edgeProperty: String = "e1"

  // Filter the graph to contain only edges matching the edgeProperty
  val filteredG = g.subgraph(epred = e => e.attr == edgeProperty)

  // Find the connected components of the subgraph, and cache it because we
  // use it more than once below
  val components: VertexRDD[VertexId] =
  filteredG.connectedComponents().vertices.cache()

  // Get the component id of the source vertex
  val sourceComponent: VertexId = components.filter {
    case (id, component) => id == sourceVertexId
  }.map(_._2).collect().head

  // Print the vertices in that component
  components.filter {
    case (id, component) => component == sourceComponent
  }.map(_._1).collect
  // => Array(1, 2, 3, 4)
}





