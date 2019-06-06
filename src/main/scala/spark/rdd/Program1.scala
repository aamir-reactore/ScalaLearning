package rdd
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
object Program1 extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val booksRDD: RDD[String] = sc.textFile("E:\\code\\bookrdd.txt")

  val c = booksRDD.count()

  println(booksRDD)
  println(booksRDD.collect().mkString(","))

  val colorsRDD = sc.parallelize(List("Red", "Blue", "Orange", "Green"))
  println(colorsRDD)
  println(colorsRDD.collect().mkString(","))
}