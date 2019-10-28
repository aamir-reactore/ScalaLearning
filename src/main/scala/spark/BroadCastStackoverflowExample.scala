package spark

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import spark.DropColumns.spark

import scala.util.Try

object BroadCastNormalizationTest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row(1, "Joe"),
    Row(2, "Moe"),
    Row(3, "Cathy"),
    Row(4, "Leo"),
    Row(5, "Matt")
  )

  val someSchema1 = List(
    StructField("primaryid", IntegerType, true),
    StructField("first_name", StringType, true)
  )
  var dfBig = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  val someData2 = Seq(
    Row("field_1", "field_1"),
    Row("Leoss", "Leonardo"),
    Row("field_1", "field_1"),
    Row("Joe", "John"),
    Row("Moe", "Marlyn"),
    Row("Cathy", "Catherine"),
    Row("Matt", "Matthew")
  )

  val someSchema2 = List(
    StructField("in_text", StringType, true),
    StructField("out_text", StringType, true)
  )
  var dfLookup = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  dfBig.show(false)
  dfLookup.show(false)

  import spark.implicits._

  val lookupMap: Map[String, String] = dfLookup
    .map { case Row(inText: String, outText: String) => (inText, outText) }
    .collect()
    .toMap

  val bc_lookupMap: Broadcast[Map[String, String]] = spark.sparkContext.broadcast(lookupMap)

  val lookupUdf: UserDefinedFunction = udf((colValue: String) => {
    val resultTry = Try(bc_lookupMap.value(colValue))
     if(resultTry.isSuccess) resultTry.get else colValue
  })

  dfBig = dfBig.withColumn("newValue", lookupUdf($"first_name"))


  dfBig.show(false)

}