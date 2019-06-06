import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{col, lit, udf, when}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object RecidWhereIn1 extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData1 = Seq(
    Row(1,"deb"),
    Row(2,"sandeep")
  )

  val someSchema1 = List(
    StructField("zs_recid", IntegerType, true),
    StructField("zs_full_name", StringType, true)
  )
  var matcherDF = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  matcherDF = matcherDF.withColumn("zs_recids_closest", lit(""))
  matcherDF = matcherDF.withColumn("zs_cluster_ids_closests", lit(""))

  val someData2 = Seq(
    Row(6,"deb",10,"c1"),
    Row(7,"deb",12,"c2"),
    Row(8,"abc", 14, "c3"),
    Row(9,"abc", 16, "c4")
  )

  val someSchema2 = List(
    StructField("zs_recid_1", IntegerType, true),
    StructField("zs_full_name", StringType, true),
    StructField("zs_recid_2", IntegerType, true),
    StructField("zs_cluster_id_2", StringType, true)
  )
  var leftRightDF = spark.createDataFrame(
    spark.sparkContext.parallelize(someData2),
    StructType(someSchema2)
  )

  val matcherDFRecids = matcherDF.select("zs_recid").collect().map(r => r.get(0))
  var finalDF = matcherDF

  lazy val updateRecIds: UserDefinedFunction = udf(() => {
  })
  var df: DataFrame = null

  matcherDF.collect() foreach { row =>
    var tempDF = leftRightDF.filter(col("zs_full_name") === row.getString(1)).toDF()
    tempDF = tempDF.select("zs_recid_2", "zs_cluster_id_2")
    val recIds = tempDF.select("zs_recid_2").collect().map(r => r.get(0)).toList.mkString(",")
    println("recIds ==>" + recIds)
    val clusterIds = tempDF.select("zs_cluster_id_2").collect().map(r => r.get(0)).toList.mkString(",")
    println("clusterIds ==>" + clusterIds)
    println("recid ==>" + row.getInt(0))
    finalDF = finalDF.withColumn("zs_recids_closest", when(col("zs_recid") === row.get(0), lit({
      recIds
    })).otherwise(col("zs_recids_closest")))

    finalDF = finalDF.withColumn("zs_cluster_ids_closests", when(col("zs_recid") === row.get(0), lit({
      clusterIds
    })).otherwise(col("zs_cluster_ids_closests")))

  }

  finalDF.show(false)
}