package wordcounts


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}

object CsvRead extends App {


  val conf = new SparkConf()
  conf.setMaster("local")
  conf.setAppName("First Application")

  val spark: SparkSession = SparkSession.builder
    .config(conf)
    .master("local")
    .appName("Spark CSV Reader")
    .getOrCreate

  val path = "E:/code/testcsv.csv"
  val df: DataFrame = spark.read.option("header", "true")
    .option("header", "true")
    .option("inferSchema", "true")
    .csv(path)



  //System.out.println("========== Print Schema ============")
  //df.printSchema
  //System.out.println("========== Print Data ==============")
  df.show
  //System.out.println("========== Print title ==============")
  //df.select("All Day Event").show*/

}
