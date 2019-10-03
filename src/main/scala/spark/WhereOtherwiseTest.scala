package spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions.{col, lit, when}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object WhereOtherwiseTest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val dataSetAccuracy = 0.9
  val matcherFixer = "matcher_fixer"
  val matcherTraining = "matcher_training"

  val someData = Seq(
    Row("2.1", "low", "0.8"),
    Row("1.0", "high","0.3"),
    Row("1.0", "medium","41.0"),
    Row("2.1", "high","33.0"),
    Row("2.1", "low","32.0"),
    Row("2.1", "low", "31.0"),
    Row("3.0", "high","62.0"),
    Row("4.0", "medium","51.0"),
    Row("4.0", "low","52.0"),
    Row("4.0", "high","52.0")
  )

  val someSchema = List(
    StructField("c1", StringType, true),
    StructField("zs_matcher_accuracy_confidence_level", StringType, true),
    StructField("c3", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  df = df.withColumn("zs_task_type", when(col("zs_matcher_accuracy_confidence_level") =!= lit("low"), lit(matcherFixer)).otherwise {
    if (dataSetAccuracy > 0.8) lit(matcherFixer) else lit(matcherTraining)
  })

  df.show(false)

}