package spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
object FixedLengthExample extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val textRDD = sc.textFile("C:\\Workspace\\ZettaSense_A\\data\\fixedlengthfiletest\\COMPANIES_4_0004_MD.dat")


    def lsplit(pos: List[Int], str: String): Row = {
      val (rest, result) = pos.foldLeft((str, List[String]())) {
        case ((s, res),curr) =>
  if(s.length()<=curr)
  {
    val split=s.substring(0).trim()
    val rest=""
    (rest, split :: res)
  }
  else if(s.length()>curr)
  {
    val split=s.substring(0, curr).trim()
    val rest=s.substring(curr)
    (rest, split :: res)
  }
  else
  {
    val split=""
    val rest=""
    (rest, split :: res)
  }
}
Row.fromSeq(result.reverse)
}

  val rdd = spark.sparkContext.textFile("C:\\Workspace\\ZettaSense_A\\data\\fixedlengthfiletest\\COMPANIES_4_0004_MD.dat")
  val metadata = spark.read.option("header", "true").csv("C:\\Workspace\\ZettaSense_A\\data\\fixedlengthfiletest\\metadata.csv")
  val header = metadata.select("col_name").rdd.map(x=>x.getString(0).trim()).collect()
  val sizeOfColumn=metadata.select("size").rdd.map(x=>x.getString(0).trim()).collect().map(_.toInt).toList
  val fields = header.map(fieldName => StructField(fieldName, StringType, nullable = true))
  val schema = StructType(fields)
  val df=spark.createDataFrame(rdd.map { x => lsplit(sizeOfColumn,x) }, schema)
  df.show(10, false)

}