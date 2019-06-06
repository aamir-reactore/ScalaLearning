package spark

import org.apache.spark.sql.functions.{col, concat_ws}
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
object MergerLookupTest extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val lookUpMap = scala.collection.mutable.Map[String, Double]()

  val someData = Seq(
    Row("zs_address_other", "popularity", 0.49),
    Row("zs_address_other", "authoritativeness", 0.24),
    Row("zs_address_other", "length", 0.96),
    Row("zs_address_other", "recency", 0.6)
  )

  val someSchema = List(
    StructField("zs_attribute_Name", StringType, true),
    StructField("zs_dimension_Name", StringType, true),
    StructField("zs_feedback_percent_yes_to_100_index", DoubleType, true)
  )
  var weightage_df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )
import spark.implicits._
  val colList = List("zs_attribute_Name", "zs_dimension_Name")
  weightage_df = weightage_df.withColumn("concat_column", concat_ws("_", colList.map(c => col(c)): _*))
  val dropList = List("zs_attribute_Name", "zs_dimension_Name")
  weightage_df = weightage_df.drop(dropList:_*)
  val mapDF = weightage_df.as[(String, String)].collect.toMap
  weightage_df.show(false)
  mapDF.foreach(println(_))


  def searchWeightage(attributeName: String, dimension: String): Double = {

    val searchKey = s"${attributeName}_$dimension"
    val weightage = lookUpMap.get(searchKey)
    if (weightage.isDefined) weightage.get else {
      println("Onlu once.................")
      val value = weightage_df.filter(col("attribute") === attributeName && col("dimension") === dimension)
        .select("zs_feedback_percent_yes_to_100_index").first().get(0).toString.toFloat
      lookUpMap.put(searchKey, value)
      value
    }

  }
}