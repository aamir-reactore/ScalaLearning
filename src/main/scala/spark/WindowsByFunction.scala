import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Dataset, Row, SparkSession}
object WindowsByFunction extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row(1, "Asia", "11-bb-22"),
    Row(2, "Asia", "x11-bb-22"),
    Row(3,"Asia",  "11-bb-22"),
    Row(4, "Asia", "11-bb-22"),
    Row(5, "Asia", "aa11-bb-22"),
    Row(6, "Europe", "x11-bb-22"),
    Row(7, "Europe","11-bb-22"),
    Row(8, "Europe","11-bb-22"),
    Row(9, "Europe","11-bb-22"),
    Row(10, "Europe","x11-bb-22"),
    Row(11, "Europe","11-bb-22"),
    Row(12, "Europe","11-bb-22"),
    Row(13, "Europe","b2aa11-bb-22"),
    Row(14, "Europe","aa11-bb-22"),
    Row(15, "Europe","x11-bb-22"),
    Row(16, "America", "b2aa11-bb-22"),
    Row(17, "America","aa11-bb-22"),
    Row(18, "America","11-bb-22"),
    Row(19, "Asia","b2aa11-bb-22"),
    Row(20, "Asia","aa11-bb-22"),
    Row(21, "Asia", "aa11-bb-22")
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

  val win_cluster = Window.partitionBy("Continent", "zs_cluster_id")
  df = df.drop("id")
  df = df.withColumn("zs_total_records", count("Continent").over(win_cluster)).distinct
  df.show(false)
}