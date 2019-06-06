import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions.{col, when}
import org.apache.spark.sql.types.{StringType, StructField, StructType}


object UnionTest extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row("rule9", "FP", "0", "0", "0", "0"),
    Row("rule9", "FN", "0", "0", "0", "0")
  )

  val someSchema = List(
    StructField("rule", StringType, true),
    StructField("zs_rule_confusion_matrix", StringType, true),
    StructField("zs_bucket1_similarity_adjustment", StringType, true),
    StructField("zs_bucket2_similarity_adjustment", StringType, true),
    StructField("zs_bucket3_similarity_adjustment", StringType, true),
    StructField("zs_bucket4_similarity_adjustment", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  val penalty = "0.4"
  val adjustment = "0.6"


  var df1 = df.filter(col("zs_rule_confusion_matrix") === "FP")
  var df2 = df.filter(col("zs_rule_confusion_matrix") === "FN")

  List("zs_bucket1_similarity_adjustment", "zs_bucket3_similarity_adjustment") foreach { x =>
    df1 = df1.withColumn(x, when(col(x) === 0,
      penalty).otherwise(col(x)))
    df2 = df2.withColumn(x, when(col(x) === 0,
      adjustment).otherwise(col(x)))
  }
  df1 = df1.drop("zs_rule_confusion_matrix")
  df1.show(false)
  df2.show(false)
  df = df1.union(df2)


}