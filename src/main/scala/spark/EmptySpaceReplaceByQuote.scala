import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object EmptySpaceReplaceByQuote extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row("1", "101", "aamir"),
    Row("2", "44", "fayaz"),
    Row("3", "17179869331", " "),
    Row("4", "3516679", " "),
    Row("5", "510", " "),
    Row("345434543", "522", " ")
  )

  val someSchema1 = List(
    StructField("zs_recId_x", StringType, true),
    StructField("zs_recid_y", StringType, true),
    StructField("name_df1", StringType, true)
  )

  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  val l:List[Any] = List(3,5,7,9,166,522)
  val df = df1.filter(col("zs_recId_x").isin(l: _*) || col("zs_recId_y").isin(l: _*))
  df.show(false)

  val f = "E:\\test.csv"
  df1.repartition(1).write.mode("overwrite").option("charset", "UTF8")
    .option("header", "true").csv(f)
}