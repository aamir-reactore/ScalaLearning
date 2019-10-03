import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Column, SparkSession}
import org.apache.spark.sql.functions._

object SparkCombinations extends App {

  val t1 = System.nanoTime


  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val myRDD: RDD[List[Int]] = sc.parallelize(List(List(1,2,3, 4, 5)))

 val result =  myRDD.flatMap(_.permutations)
  import spark.implicits._

  var df = result.toDF("listvalues")

  def stringify(c: Column) = concat(lit("["), concat_ws(",", c), lit("]"))

  df = df.withColumn("listvalues", stringify($"listvalues"))

  df.show()

 val o_file = "C:\\Workspace\\ZettaSense_A\\data.csv"
  df.write.mode("overwrite")
    .option("header", "true").csv(o_file)

  val duration = (System.nanoTime - t1) / 1e9d
  Thread.sleep(10)
  println("Duration: ==> " + duration)

}