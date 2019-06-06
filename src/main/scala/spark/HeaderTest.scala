import WindowsByFunction.{someData, spark}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object HeaderTest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  import spark.implicits._

  // var listCols =  List("similarity_zs_bucket_1", "similarity_zs_bucket_2", "similarity_zs_bucket_3", "similarity_zs_bucket_4", "similarity_zs_bucket_234", "zs_cluster_link_strength", " zs_recid_x", " zs_recid_y")
  val someData = Seq(
    Row(1, "Asia", "11-bb-22"),
    Row(2, "Asia", "x11-bb-22")
  )

  val someSchema = List(
    StructField("id", IntegerType, true),
    StructField("Continent", StringType, true),
    StructField("zs_cluster_id", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  df = df.withColumn("name", lit("ana"))

  if (df.isEmpty) {
    println("empty dataframe")
  } else {
    df.show()

  }
  /* println(df.count())

   val f = "E:\\data.csv"
   df.write.mode("overwrite")
     .option("header", "true").csv(f)*/

}