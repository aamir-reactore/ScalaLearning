package spark

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import spark.DropColumns.spark

object BroadCastTest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row(1, 0, 2),
    Row(2, 0, 2),
    Row(3, 1, 1),
    Row(4, 1, 1),
    Row(5, 1, 1)
  )

  val someSchema1 = List(
    StructField("primaryid", IntegerType, true),
    StructField("field_1", IntegerType, true),
    StructField("field_2", IntegerType, true)
  )
  var dfBig = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  val someData2 = Seq(
    Row("field_1", 0, 8),
    Row("field_1", 1, 23),
    Row("field_2", 2, 33),
    Row("field_2", 1, 50),
    Row("field_2", 2, 60)
  )

  val someSchema2 = List(
    StructField("category", StringType, true),
    StructField("field_value", IntegerType, true),
    StructField("ratios", IntegerType, true)
  )
  var dfLookup = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  // dfBig.show(false)
  //dfLookup.show(false)

  import spark.implicits._

  val lookupMap = dfLookup
    .map { case Row(category: String, field_values: Int, ratio: Int) => ((category, field_values), ratio) }
    .collect()
    .toMap

  val bc_lookupMap: Broadcast[Predef.Map[(String, Int), Int]] = spark.sparkContext.broadcast(lookupMap)

  val lookupUdf = udf((field1: Int, field2: Int) =>
    (bc_lookupMap.value(("field_1", field1)), bc_lookupMap.value(("field_2", field2)))
  )

  dfBig = dfBig.withColumn("udfResult", lookupUdf($"field_1", $"field_2"))
    .select($"primaryId", $"udfResult._1".as("field_1"), $"udfResult._2".as("field_2"))


  dfBig.show(false)
}

