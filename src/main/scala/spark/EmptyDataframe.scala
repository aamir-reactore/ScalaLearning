import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

import scala.util.Try

object EmptyDataframe extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val someData1 = Seq(
    Row("1", "1,0,0,0,0", "FN"),
    Row("2", "0,,0,0,0,0", "TN"),
    Row("3", "0,,0,0,0,0", "TP"),
    Row("4", "0,,0,0,0,0", "FP"),
    Row("5", "1,0,0,0,0", "TN")
  )

  val someSchema1 = List(
    StructField("c1", StringType, true),
    StructField("c2", StringType, true),
    StructField("name", StringType, true)
  )
  val df: DataFrame = null//spark.createDataFrame(sc.emptyRDD[Row].setName("empty"), StructType(Nil))

  val sizeCheck = Try {
    df.head(1).isEmpty
  }
  if(sizeCheck.isFailure) {
     println("empty df")
  } else {
    if(sizeCheck.get) println("empty")
  }
}