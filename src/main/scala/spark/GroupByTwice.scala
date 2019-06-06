import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object GroupByTwice extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row(1, "TP", "1"),
    Row(1, "FN", "0"),
    Row(2, "FP", "1"),
    Row(2, "TN", "1"),
    Row(3, "TP", "1"),
    Row(3, "TP", "1")
/*    Row(7, "FN", "1"),
    Row(8, "TP", "0"),
    Row(9, "FN", "1"),
    Row(10, "FN", "1"),
    Row(11, "TP", "0"),
    Row(12, "FN", "1"),
    Row(13, "TP", "1"),
    Row(14, "FN", "0"),
    Row(15, "FP", "1"),
    Row(16, "FP", "1")*/
  )

  val win_cluster = Window.partitionBy("zs_rule_confusion_matrix")
  val someSchema = List(
    StructField("id", IntegerType, true),
    StructField("zs_rule_confusion_matrix", StringType, true),
    StructField("zs_feedback_id", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  df = df.dropDuplicates("id")
  df.show(false)

/*

  import spark.implicits._
  val res = df.agg(sum("zs_feedback_id")).as[String].collect().headOption.getOrElse(0L)

  println("res" + res)
  val confusionMatrixDF = df.select("zs_rule_confusion_matrix")*/


}
