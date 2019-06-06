import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object UnionColumnRename extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row("1.0", "4.0"),
    Row("1.0", "4.0"),
    Row("2.1", "3.0"),
    Row("2.1", "3.0"),
    Row("2.1", "3.0")
  )

  val someSchema1 = List(
    StructField("c1", StringType, true),
    StructField("c2", StringType, true)
  )
  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  val someData2 = Seq(
    Row("2.1", "3.0"),
    Row("3.0", "6.0"),
    Row("4.0", "5.0"),
    Row("4.0", "5.0"),
    Row("4.0", "5.0")
  )

  val someSchema2 = List(
    StructField("c3", StringType, true),
    StructField("c4", StringType, true)
  )
  var df2 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  val resDf = df2.union(df1).withColumnRenamed("c3", "col1").withColumnRenamed("c4", "col2")
  resDf.show(false)

}