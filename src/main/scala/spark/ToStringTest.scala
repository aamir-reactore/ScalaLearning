import org.apache.spark.sql.SparkSession

object ToStringTest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  import spark.implicits._

  val df = List((1, 2), (3, 4)).toDF("col1", "col2")

  df.show(false)

  println(df.first.get(0).toString)
}