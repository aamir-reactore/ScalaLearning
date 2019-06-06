import jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.functions._
object DistinctColumn extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row("rule9", "1,2,3,4,4,2,2")
  )

  val someSchema = List(
    StructField("rule", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )
  val f1 = udf((arraystring: String) => {
    println("::" + arraystring)
    val r = arraystring.split(",").distinct.mkString(",")
    println(">>" + r)
    r
  })

  df = df.withColumn("arrayvalue", lit("2,2,3,3,5,6"))
  df = df.withColumn("arrayvalue", f1(col("arrayvalue")))
  df.show(false)

}