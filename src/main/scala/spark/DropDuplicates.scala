import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object DropDuplicates extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row("2.1", "32.0", "322.0"),
    Row("1.0", "1,2,3,4","42.0"),
    Row("1.0", "1,2,3,4","41.0"),
    Row("2.1", "32.0","33.0"),
    Row("2.1", "32.0","32.0"),
    Row("2.1", "32.0", "31.0"),
    Row("3.0", "6.0","62.0"),
    Row("4.0", "52.0","51.0"),
    Row("4.0", "52.0","52.0"),
    Row("4.0", "52.0","52.0")
  )

  val someSchema = List(
    StructField("c1", StringType, true),
    StructField("c2", StringType, true),
    StructField("c3", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )
  println(df.count())
  df = df.dropDuplicates()
  df.show(false)
  println(df.count())

}