package spark

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import spark.DropColumns.{someData1, someSchema1, spark}
import org.apache.spark.sql.functions._
object SupervisedTestJoin extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row("xxx11", "yyy11", 0.49),
    Row("xxx22", "yyy22", 0.24),
    Row("xxx33", "yyy33", 0.96),
    Row("xxx44", "yyy44", 0.6),
    Row("xxx55", "yyy55", 0.6),
    Row("xxx66", "yyy66", 0.6)
  )

  val someSchema1 = List(
    StructField("zs_cluster_left", StringType, true),
    StructField("zs_cluster_right", StringType, true),
    StructField("confusion_matrix", DoubleType, true)
  )

  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )
  val someData2 = Seq(
    Row("xxx11"),
    Row("xxx22"),
    Row("xxx33"),
    Row("yyy33"),
    Row("yyy66"),
    Row("sdfasdf"),
    Row("xxx66")
  )

  val someSchema2 = List(
    StructField("zs_cluster_id", StringType, true)
  )


  var df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  val cList = df2.select("zs_cluster_id").rdd.map(r => r.getString(0)).collect().distinct


  var res2: DataFrame = df1.join(df2, df1.col("zs_cluster_right") === df2.col("zs_cluster_id"))


  res2 = res2.withColumn("temp", when(col("zs_cluster_left").isin(cList: _*), lit({"true"})).otherwise(lit("false")))
  res2 = res2.filter(col("temp") === "true")

  res2.show(false)

}