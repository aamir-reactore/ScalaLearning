package spark.sparkbook

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object SchemaSortTest extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val customSchema = StructType(Array(StructField("dest_country_name", StringType, true), StructField("origin_country_name", StringType, true), StructField("count", IntegerType, true)))

  var fDF = spark.read.format("csv").
    option("header", "true").
    option("delimiter", "\t").
    option("timestampFormat", "yyyy/MM/dd HH:mm:ss")
    .schema(customSchema).load("E:/flightdata.csv")
  //fDF.createOrReplaceTempView("flightdata_tbl")

  //fDF = spark.sql("""SELECT DEST_COUNTRY_NAME, count(1) FROM flightdata_tbl GROUP BY DEST_COUNTRY_NAME""")

  import org.apache.spark.sql.functions._
  var res = fDF.select(max("count")).take(1)
   val rr  = res.map(_.get(0)).head
}