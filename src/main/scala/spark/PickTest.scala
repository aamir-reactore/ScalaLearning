import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.{coalesce, to_date}
import org.apache.spark.sql.types.DateType
object PickTest extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row(1, "1", "2"),
    Row(2, "1", "3"),
    Row(3, "4", "3")
  )

  val someSchema = List(
    StructField("id", IntegerType, true),
    StructField("firstname", StringType, true),
    StructField("secondname", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  //df = df.withColumn("levenstein", levenshtein(col("firstname"), col("secondname")))

  //println(df.head(1).isEmpty)
  //println(df.head(1))
   val x = df.select(explode(array("id", "firstname", "secondname"))).distinct.collect().map(r => r.getString(0))
   x.foreach(println)
println("**")
  val xx = spark.emptyDataFrame
  println(xx == null)
  println(xx.isEmpty)
  println(x.isEmpty)
  println(xx.head(1).isEmpty)
}