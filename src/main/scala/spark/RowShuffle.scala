import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object ShuffleTest extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row("1.0", "4.0"),
    Row("1.0", "4.0"),
    Row("2.1", "3.0"),
    Row("2.1", "3.0"),
    Row("2.1", "3.0"),
    Row("2.1", "3.0"),
    Row("3.0", "6.0"),
    Row("4.0", "5.0"),
    Row("4.0", "5.0"),
    Row("4.0", "5.0")
  )

  val someSchema = List(
    StructField("c1", StringType, true),
    StructField("c2", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  df.show(false)

  import org.apache.spark.sql.functions.rand
  val shuffledDF = df.orderBy(rand())
  shuffledDF.show(false)

}
