import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions.{col, when}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._
object UDFListtest extends App {

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
}

object UDFtest2 extends App {
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



  def udf_name(List_name:List[String]): UserDefinedFunction = {
    udf((name:String) => {
      List_name.contains(name)
    })
  }

  val List_name : List[String] = List("a", "b", "c")

  df = df.withColumn("is_name_in_list", udf_name(List_name)(col("zs_rule_confusion_matrix")))
  df.show(false)
}