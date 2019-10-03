package spark

import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

object DataTypeDF extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row("2.1", "32.0", "322.0", false),
    Row("1.0", "1,2,3,4","42.0", false),
    Row("1.0", "1,2,3,4","41.0", false),
    Row("2.1", "32.0","33.0", false),
    Row("2.1", "32.0","32.0", false),
    Row("2.1", "32.0", "31.0", false),
    Row("3.0", "6.0","62.0", false),
    Row("4.0", "52.0","51.0", false),
    Row("4.0", "52.0","52.0", false),
    Row("4.0", "52.0","52.0", false)
  )

  val someSchema = List(
    StructField("c1", StringType, true),
    StructField("c2", DoubleType, true),
    StructField("c3", IntegerType, true),
    StructField("c3", BooleanType, true)
  )
  var inputDF = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  val inputDFCols:Array[String] = inputDF.columns
  val columnDataTypes : Array[String] = inputDF.schema.fields.map(x=>x.dataType).map(x=>x.toString)

  val res: Array[(String, String)] = inputDFCols zip columnDataTypes
  res foreach println

}