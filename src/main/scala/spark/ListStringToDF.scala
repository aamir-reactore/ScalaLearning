package spark

import org.apache.spark.sql.SparkSession

object ListStringToDF extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val l = List("first_name", "last_name")

  import spark.implicits._
  val df = l.toDF("cols")

  df.show()
}