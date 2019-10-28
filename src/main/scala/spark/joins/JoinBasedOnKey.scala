package spark.joins

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object JoinBasedOnKey extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  import spark.implicits._

  val df1 = List("k1", "k2", "k3").toDF("key")
  val df2 = List(
    ("t1", Array("k1", "k2")),
    ("t2", Array("pk1", "pk2"))
  ).toDF("topic", "keys")
  val result = df1.join(df2, expr("array_contains(keys,key)"))
  result.show(false)

}