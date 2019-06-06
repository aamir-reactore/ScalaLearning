import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object DataframeRuleSImilarity extends App {

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

  df.show(false)

  var df1 = df.filter(col("zs_rule_confusion_matrix") === "FP")
  var df2 = df.filter(col("zs_rule_confusion_matrix") === "FN")

  List("zs_bucket1_similarity_adjustment", "zs_bucket3_similarity_adjustment") foreach { x =>
    df1 = df1.withColumn(x, when(col(x) === 0,
      penalty).otherwise(col(x)))
    df2 = df2.withColumn(x, when(col(x) === 0,
      adjustment).otherwise(col(x)))
  }

  df = df1.union(df2)
  def test_udf_higher_order(l1: List[Double], l2: List[Double], df: DataFrame) = {
    udf((similarityBucketScore: Double,adjustmentSimilarityName: String, somevalue:String) => {

      println("lenght1 ==>" + l1.contains(similarityBucketScore))
      println("lenght2 ==>" + l2.contains(similarityBucketScore))
      println("similarityBS ==>" + similarityBucketScore)
      println("adjustmentSimilarityName ==>" + adjustmentSimilarityName)
      println("someValue ==>" + somevalue)
      12
    })
  }


  df = df.withColumn("testColumn", test_udf_higher_order(List(1.0,2.0), List(4.0,6.0), df1)(col("zs_bucket1_similarity_adjustment"), col("zs_rule_confusion_matrix"), lit("hello")))
  df.show(false)


}

