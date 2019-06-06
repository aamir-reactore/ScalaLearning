package joins

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions.{col, max}

object Crossjoin extends App {


  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row(1, "abc1"),
    Row(2, "def1"),
    Row(3, "ghi1")
  )

  val someData2 = Seq(
    Row(3, "abc2"),
    Row(1, "def2"),
    Row(2, "ghi2")
  )

  val someSchema1 = List(
    StructField("id1", IntegerType, true),
    StructField("name1", StringType, true)
  )

  val someSchema2 = List(
    StructField("id2", IntegerType, true),
    StructField("name2", StringType, true)
  )
  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  var df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  val resDF = df1.crossJoin(df2)

  resDF.show(false)
}

object InnerJoinTest1 extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row(2, 0),
    Row(4, 1),
    Row(11, 1)
  )

  val someData2 = Seq(
    Row(2, 4, "kim"),
    Row(20, 60, "zack"),
    Row(10, 11, "sean")
  )

  val someSchema1 = List(
    StructField("zs_recid"      , IntegerType, true),
    StructField("zs_feedback_id", IntegerType, true)
  )

  val someSchema2 = List(
    StructField("zs_recid_1", IntegerType, true),
    StructField("zs_recid_2", IntegerType, true),
    StructField("surname", StringType, true)
  )
  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  var df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  var finalDF = df2.join(df1, df2.col("zs_recid_1") === df1.col("zs_recid")/*, joinType = "left_outer"*/)

  finalDF = finalDF.withColumnRenamed("zs_feedback_id", "zs_feedback_id_1")
  finalDF.show(false)

  val cols = df1.columns.map(x => s"${x}_tmp")
  df1 = df1.toDF(cols: _*)
  df1.show(false)
  finalDF = finalDF.join(df1, finalDF.col("zs_recid_2") === df1.col("zs_recid_tmp")/*, joinType = "left_outer"*/)
  finalDF = finalDF.withColumnRenamed("zs_feedback_id_tmp", "zs_feedback_id_2")
  val dropList = List("zs_recid_tmp", "zs_recid")
  finalDF.show(false)

  finalDF = finalDF.drop(dropList: _*).filter(col("zs_feedback_id_1").isNotNull || col("zs_feedback_id_2").isNotNull)
  finalDF.show(false)
}