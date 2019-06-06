import org.apache.spark.sql.functions.{col, lit, when}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object RowByName extends App {
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row("1", "2", "aamir", "1,2,3,4,5"),
    Row("2", "3", "fayaz", "2,5,6,4,3"),
    Row("3", "5", "wani", "9,6,5,4,3")
  )

  val someSchema1 = List(
    StructField("id", StringType, true),
    StructField("money", StringType, true),
    StructField("name", StringType, true),
    StructField("recids", StringType, true)
  )

  var df1 = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  df1.collect() foreach { row =>
    val r = row.getAs[String]("name")
    println(r)
  }

  var df = df1
  df1.collect() foreach { row =>

    val recidIdsString = row.getAs[String]("recids")
    val recIds = recidIdsString + "," + "5,12,32"
    df = df.withColumn("recids", when(col("id") === row.getAs[Any]("id"), lit({
      recIds
    })).otherwise(col("recids")))
  }

  df.show(false)
}