package wordcounts

import java.util.Scanner

import org.apache.spark.{SparkConf, SparkContext}

object CountWordsInFile extends App {


  val conf = new SparkConf()
  conf.setMaster("local")
  conf.setAppName("First Application")

  val sc = new SparkContext(conf)

  val inputFile = sc.textFile("E:\\code\\SparkLearning\\sparktest.txt")
  val counts = inputFile.flatMap{ line =>
    line.split(" ")
  }.map{ word =>
    (word, 1)
  }.reduceByKey(_ + _)
  counts.saveAsTextFile("E:/SparkTest")


}