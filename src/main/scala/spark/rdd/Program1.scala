package spark.rdd

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import spark.rdd.Program1A.colorsRDD

//ways to create rdd
object Program1A extends App {

  val spark: SparkSession = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc: SparkContext = spark.sparkContext

  val booksRDD: RDD[String] = sc.textFile("E:\\code\\bookrdd.txt")

  val c = booksRDD.count()

  println(booksRDD)
  println(booksRDD.collect().mkString(","))

  val colorsRDD = sc.parallelize(List("Red", "Blue", "Orange", "Green"))
  println(colorsRDD)
  println(colorsRDD.collect().mkString(","))


}

object Program1B extends App {

  val spark: SparkSession = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc: SparkContext = spark.sparkContext


  val text = sc.textFile("E:/mytextfile.txt")
  val countsRDD = text.flatMap(line => line.split(" ")).map(word => (word, 1))
    .reduceByKey((accumulatedValue: Int, currentValue: Int) => accumulatedValue + currentValue)
  println(countsRDD.collect().mkString(","))

}