package spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.col

object LoadCsvTest extends App {
  val  rule = "zs_rule_32"

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val f = "C:\\Users\\Aamir Fayaz\\Documents\\bucketadjustments.csv"

  val df = loadCsv(f, true, ",")

  val res = df.filter(col("rule") === rule)

  res.show(false)
  def loadCsv(i_file: String, header: Boolean, delimiter: String, log_info: Boolean = true): DataFrame = {
    spark.read
      .format("csv")
      .option("delimiter", delimiter)
      .option("header", header)
      //.option("escape","\"")
      .option("multiline", true)
      .option("ignoreTrailingWhiteSpace", true)
      .option("inferSchema", "true")
      .option("timestampFormat", "yyyy/MM/dd HH:mm:ss")
      .load(i_file)
  }
}