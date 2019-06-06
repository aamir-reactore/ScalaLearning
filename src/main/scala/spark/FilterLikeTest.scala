import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions._
object FilterLikeTest extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row("2", "fayaz"),
    Row("3", "wani"),
    Row("1", "aamirfaw"),
    Row("4", "aamir"),
    Row("5", "aamirfayaz"),
    Row("6", "wani")
  )

  val someSchema = List(
    StructField("ïd", StringType, true),
    StructField("name", StringType, true)
  )

  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  df1 = df1.filter(col("name").like("aamir"))
  df1.show(false)

}