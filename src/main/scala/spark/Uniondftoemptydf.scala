package spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import spark.DataTypeDF.{someData, someSchema, spark}

object Uniondftoemptydf extends App {
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
    StructField("c1", IntegerType, true),
    StructField("c2", StringType, true),
    StructField("name", StringType, true)
  )

  var inputDF = spark.createDataFrame(
    spark.sparkContext.parallelize(someData1),
    StructType(someSchema1)
  )

  val incomDF = spark.emptyDataFrame
  val res  = inputDF.union(incomDF)

  res.show(false)
}

object jj extends App {

  class Animal(name: String)
  class Dog(name: String) extends Animal(name)

  val x:Animal = new Dog("ramhoon")

  val a1 = new Animal("")
  val a2 = new Animal("")
  val a3 = new Animal("")

  val aL = List[Animal](a1, a2, a3)

  val d1 = new Dog("")
  val d2 = new Dog("")
  val d3 = new Dog("")

  val dL: List[Dog] = List[Dog](d1, d2, d3)

  val l: List[Animal] = dL
}

