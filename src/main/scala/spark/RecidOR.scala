import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._
object RecidOR extends App {


  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row(1, "TP",0),
    Row(2, "FN",1),
    Row(3, "FP",3),
    Row(3, "FP",1),
    Row(4, "TN",1)
  )

  val someData2 = Seq(
    Row(1, 4, "adam"),
    Row(20, 11, "Kim"),
    Row(6, 12, "jason"),
    Row(10, 60, "steve")
  )

  val someSchema1 = List(
    StructField("zs_recid", IntegerType, true),
    StructField("zs_rule_confusion_matrix", StringType, true),
    StructField("zs_feedback_id", IntegerType, true)
  )

  val someSchema2 = List(
    StructField("zs_recid_x", IntegerType, true),
    StructField("zs_recid_y", IntegerType, true),
    StructField("name", StringType, true)
  )

  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  var df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  val x1 = df1.filter(col("zs_rule_confusion_matrix") === "FP" && col("zs_feedback_id") === "1")
  x1.show(false)

/*  val matcherDistinctRecids = df1.select("zs_recid").distinct().collect.map(_.get(0).toString)

  df2 = df2.filter(col("zs_recid_x").isin(matcherDistinctRecids: _*) || col("zs_recid_y").isin(matcherDistinctRecids:_*))
  df2.show(false)



   val df1cols = df1.columns.map(x => s"${x}_matcher")
   val df1_Matcher = df1.toDF(df1cols: _*)

  df2 = df2.join(df1_Matcher, df2.col("zs_recid_x") === df1_Matcher.col("zs_recid_matcher"))
  println("**")
  df2.show(false)

  df2 = df2.withColumnRenamed("zs_feedback_id_matcher", "zs_feedback_id_1")
  println("===")
  df2.show(false)
  df2 = df2.join(df1, df2.col("zs_recid_y") === df1.col("zs_recid"))
  df2 = df2.withColumnRenamed("zs_feedback_id", "zs_feedback_id_2")

  val dropList = List("zs_recid", "zs_recid_matcher")
  df2 = df2.drop(dropList: _*)
  df2.show(false)*/

}