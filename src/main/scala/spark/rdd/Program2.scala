package spark.rdd

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import spark.rdd.Program2DoubtClearance.getClass

import scala.io.Source
object Program2 extends App {

  val spark: SparkSession = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc: SparkContext = spark.sparkContext

  val stringRDD: RDD[String] = sc.textFile("E:\\code\\ScalaLearning\\src\\main\\scala\\spark\\rdd\\rddtest.txt")
  val rddArray: RDD[Array[String]] = stringRDD.map{ x => x.split("\n")}
  val kVRDD: RDD[(String, Int)] = rddArray.map{ x => (x(0), 1)}
  kVRDD.count()
  //kVRDD.collect().foreach(println)
  //val fCountRDD =
}

object Program2DoubtClearance extends App {
  val str = Source.fromFile("E:\\code\\ScalaLearning\\src\\main\\scala\\spark\\rdd\\rddtest.txt").getLines.mkString
  val r: Array[String] = str.split("/")
  r foreach println
  println(r.length)

  println("==================")

  val a = Array("Hello", "How", "are", "you")
  println(a(0))
}