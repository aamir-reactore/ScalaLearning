import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object jointest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row("1", "2", "aamir"),
    Row("2", "3", "fayaz"),
    Row("3", "5", "wani")
  )

  val someSchema1 = List(
    StructField("recid_df1_x", StringType, true),
    StructField("recid_df1_y", StringType, true),
    StructField("name_df1", StringType, true)
  )

  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  val someData2 = Seq(
    Row("2", "2", "sandeep"),
    Row("3", "3", "kumar"),
    Row("1", "9", "patakamuri"),
    Row("1", "9", "sandeep man"),
    Row("1", "9", "sandeep jjl"),
    Row("1", "9", "sadep jjl")
  )

  val someSchema2 = List(
    StructField("recid_df2_x", StringType, true),
    StructField("recid_df2_y", StringType, true),
    StructField("name_df2", StringType, true)
  )

  var df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )
 import org.apache.spark.sql.functions._

  val x = df2.filter(col("name_df2").contains("sandeep"))
  x.show(false)

  var res = df1.join(df2, df1.col("recid_df1_x") === df2.col("recid_df2_x") && df1.col("recid_df1_y") === df2.col("recid_df2_y"), joinType = "full_outer")


  res.show(false)


  res = res.withColumn("recid_df1_x", when(col("recid_df1_x").isNull, col("recid_df2_x")).otherwise(col("recid_df1_x")))
   res = res.withColumn("recid_df1_y", when(col("recid_df1_y").isNull, col("recid_df2_y")).otherwise(col("recid_df1_y")))


  res.show(false)

}