package spark

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{lit, mean}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object MeanOfCols extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val dataSetAccuracy = 0.9
  val matcherFixer = "matcher_fixer"
  val matcherTraining = "matcher_training"

  val someData = Seq(
    Row("1.0"),
    Row("1.0"),
    Row("2.0"),
    Row("2.0"),
    Row("2.0"),
    Row("2.0"),
    Row("3.0"),
    Row("4.0"),
    Row("4.0"),
    Row("4.0")
  )

  val someSchema = List(
    StructField("c1", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  val win_cluster_Dummy_value = Window.partitionBy("Dummy_value")

   df = df.withColumn("Dummy_value", lit(1))
  val meanDF = df.withColumn("zs_merger_accuracy_confidence_mean", mean("c1").
    over(win_cluster_Dummy_value)).
    select("zs_merger_accuracy_confidence_mean").
    first().getDouble(0)

  println( meanDF )
}
