package spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.col

import scala.collection.mutable.ListBuffer
import scala.util.Try

object LoadCsvTest extends App {
  val rule = "zs_rule_32"

   case class EntityRule(entityRuleId: Long)
   val entityRules = List(EntityRule(33), EntityRule(43), EntityRule(25), EntityRule(47))
  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext
  val f = "E:\\bucketadjustments.csv"

  val prev_rules = List("zs_rule_35", "zs_rule_32")
  val df = loadCsv(f, true, ",")

  //val res = df.filter(col("rule") === rule)

  //val rulesListHDFS: Array[(String, Long)] = zsContext.dataHandler.load(spark, file_rules_count, false).collect().map(x => (x.get(0), x.get(1))).map(x => (x._1, x._2.toString.toLong)).sortWith(_._2 > _._2)

  val result1 = df.collect().map(x => (x.getAs[String]("rule"), x.getAs[Integer]("sortorder")))
  val result2: Array[(String, Integer)] = result1.distinct.sortBy(_._2)
  result2.foreach(x => println(x._1 + " : " + x._2))

  val filteredRules = filterRuleDataFrameForActiveness(result2.toList.map(_._1))
  val finalRes = result2.filterNot(x => filteredRules.contains(x._1))
  println("=============================================")
  finalRes.foreach(x => println(x._1 + " : " + x._2))
  println("**************************************************************")
  val x = finalRes.sortBy(_._2).map(_._1)

  val res = x diff prev_rules

  println(res.head)


  def loadCsv(i_file: String, header: Boolean, delimiter: String, log_info: Boolean = true): DataFrame = {
    spark.read
      .format("csv")
      .option("delimiter", delimiter)
      .option("header", header)
      //.option("escape","\"")
      .option("multiline", true)
      .option("ignoreTrailingWhiteSpace", true)
      .option("inferSchema", "true")
      .option("timestampFormat", "yyyy/MM/dd HH:mm:ss")
      .load(i_file)
  }

  private def filterRuleDataFrameForActiveness(ruleList: List[String]): List[String] = {

    val filterRulesList = new ListBuffer[String]
    ruleList foreach { rule =>
      val ruleIdTry = Try(rule.substring(rule.lastIndexOf("_") + 1).toInt)

      if (ruleIdTry.isSuccess) {
        val entityRuleOptional = entityRules.find(_.entityRuleId == ruleIdTry.get)
        if (entityRuleOptional.isDefined) {
          filterRulesList.append(rule)
        }
      }

    }
    filterRulesList.toList

  }
}


