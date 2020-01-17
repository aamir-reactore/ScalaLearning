import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.Column
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{coalesce, to_date}
import org.apache.spark.sql.types.DateType
object lev_null_check extends App {

  val l = List(1)
 val x = l.head
  val y =  l.tail
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val someData = Seq(
    Row(1, null, "abc"),
    Row(2, "janny", "reinner"),
    Row(3, "44", null),
    Row(4, null, null),
    Row(5, "abc", null),
    Row(6, "lkn", "sdfs")
  )

  val someSchema = List(
    StructField("id", IntegerType, true),
    StructField("firstname", StringType, true),
    StructField("secondname", StringType, true)
  )
  var df = spark.createDataFrame(
    spark.sparkContext.parallelize(someData),
    StructType(someSchema)
  )

  val xx:UserDefinedFunction = udf((firstName: String, secondName: String) => {
       if(firstName == null || secondName == null) "false" else "true"
  })


   val modifiedLevenstienUDF = udf((levenstienScore: Any, s1: String, s2: String) => {
     println("levensteinScore: " + levenstienScore)
     println("Option(levenstienScore).isEmpty: " + Option(levenstienScore).isEmpty)
     println("Option(levenstienScore).isDefined: " + Option(levenstienScore).isDefined)
    if(Option(levenstienScore).isEmpty) 0.0 else {
      if(s1 == null || s1 == "" || s1 == "ana" || s2 == null || s2 == "" || s2 == "ana") 0.0 else {
        val s1Len = s1.length
        val s2Len = s2.length
        val maxLength = math.max(s1Len, s2Len)
        1.toDouble - (levenstienScore.asInstanceOf[Int] / maxLength.toDouble)
      }
    }

  })
  val nullCheck = udf((columnValue: String, previousValue: String) => {
    if(previousValue == "") {
      if(columnValue == null || columnValue.isEmpty || columnValue == "" || Option(columnValue).isEmpty) "true" else "false"
    } else {
      if(previousValue == "true") previousValue else {
        if(columnValue == null || columnValue.isEmpty || columnValue == "" || Option(columnValue).isEmpty) "true" else "false"
      }
    }
  })

  val finalNullCheck = udf((nullCol: String) => {
   if(nullCol == "true") 1 else 0
  })
  val ruleAttributes = List("firstname", "secondname")

  df = df.withColumn("mod_lev", modifiedLevenstienUDF(levenshtein(col("firstname"), col("secondname")),
    col("firstname"), col("secondname")))
  //df = df.withColumn("check", xx(col("firstname"), col("secondname")))
  df = df.withColumn("is_null", nullCheck(col(ruleAttributes.head), lit("")))
  ruleAttributes.tail foreach { attribute =>
    df = df.withColumn("is_null", nullCheck(col(attribute), col("is_null")))
  }

  df = df.withColumn("final", finalNullCheck(col("is_null")))
  df.show(false)

/*  //println(df.head(1).isEmpty)
  //println(df.head(1))
   val x = df.select(explode(array("id", "firstname", "secondname"))).distinct.collect().map(r => r.getString(0))
   x.foreach(println)
println("**")
  val xx = spark.emptyDataFrame
  println(xx == null)
  println(xx.isEmpty)
  println(x.isEmpty)
  println(xx.head(1).isEmpty)*/
}