package spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
object SchemaSortTest extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

 val customSchema = StructType(Array(StructField("dest_country_name", StringType, true), StructField("origin_country_name",StringType,true),StructField("count", IntegerType,true)))

  val fDF = spark.read
    .format("csv")
    .option("header", "true")
    .option("delimiter", "\t")
    .option("timestampFormat", "yyyy/MM/dd HH:mm:ss")
    .schema(customSchema)
    .load("E:/flightdata.csv")
  fDF.show(false)

  fDF.printSchema()

  //val sortedDF = fDF.sor
}