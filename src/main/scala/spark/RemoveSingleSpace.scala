package spark

import org.apache.spark.sql.{DataFrame, SparkSession, functions}

object RemoveSingleSpace extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  val l = List("zs_entity_type", "zs_match_condition_1", "aa", "bb")

  val df = createRuleDataFrame
  df.show(10, false)
  val resDF = removeExtraChars(df, l)
  resDF.show(10, false)

  private def removeExtraChars(df: DataFrame, bucketList: List[String]): DataFrame = {
    if (bucketList.length == 4) {
      val colList = List("zs_entity_type", "zs_match_condition_1")
      removeSingleSpace(df, colList)
    } else if (bucketList.length == 3) {
      val colList = List("zs_bucket_1", "zs_bucket_2", "zs_bucket_3")
      removeSingleSpace(df, colList)
    } else if (bucketList.length == 2) {
      val colList = List("zs_bucket_1", "zs_bucket_2")
      removeSingleSpace(df, colList)
    } else {
      removeSingleSpace(df, List("zs_bucket_1"))
    }
  }

  private def removeSingleSpace(df: DataFrame, colList: List[String]): DataFrame = {
    var resDF = df
    for (colName <- colList) {
      resDF = resDF.withColumn(colName, functions.regexp_replace(resDF.col(colName), "\\s{1,}", ""))
    }
    resDF
  }



  def createRuleDataFrame = {
    val rulesList = List(
      (1, "Institution ABC", "zs_full_name", "similar how are you", "zs_lei", "exact"),
      (2, "Institution DEF", "zs_full_name", "similarmand how are you", "zs_tax_identifier", "exact"),
      (3, "Institution FGR", "zs_full_name", "similar patkuamiri", "zs_avox", "exact"),
      (4, "Institution DDD", "zs_full_name", "similar asdf sdf ", "Exact Same DUNS Identifier", "exact"),
      (5, "Institution EDB", "zs_full_name", "similar", "Exact Same Bloomberg Identifier", "exact"),
      (6, "Institution EDN", "zs_full_name", "similar", "zs_swift_bic", "exact"),
      (7, "Institution NBN", "zs_full_name", "similar", "zs_central_index_key", "exact"),
      (8, "Institution", "zs_full_name", "similar", "zs_date_incorporation", "exact"),
      (9, "Institution", "zs_full_name", "similar", "Date of Formation", "exact"),
      (10, "Institution", "zs_full_name", "similar", "zs_website_url", "exact"),
      (11, "Institution", "zs_full_name", "similar", "zs_sic_code", "exact"),
      (12, "Institution ", "zs_full_name", "similar", "zs_nace_code", "exact"),
      (13, "Institution", "zs_full_name", "similar", "zs_naics_description", "exact"),
      (14, "Institution", "zs_full_name", "similar", "Full Address of Incorporation", "exact"),
      (15, "Institution", "zs_full_name", "similar", "Street Address", "exact"),
      (16, "Institution", "zs_full_name", "similar", "City", "exact"),
      (17, "Institution", "zs_full_name", "similar", "State", "exact"),
      (18, "Institution", "zs_full_name", "similar", "Postal Code", "exact"),
      (19, "Institution", "zs_full_name", "similar", "Country", "exact"),
      (20, "Institution", "zs_full_name", "similar", "Full Address of Registration", "exact"),
      (21, "Institution", "zs_full_name", "similar", "Full Primary Operation Address", "exact"),
      (22, "Institution", "zs_full_name", "similar", "Full Headquarters Address", "exact"),
      (23, "Institution", "zs_full_name", "similar", "Full Other Operation Address", "exact"),
      (24, "Institution", "zs_full_name", "similar", "zs_tax_jurisdiction_code", "exact"),
      (25, "Institution", "zs_full_name", "similar", "Fax Number", "exact"),
      (26, "Institution", "zs_full_name", "similar", "Tax Authority", "exact"),
      (27, "Institution", "zs_full_name", "similar", "zs_primary_regulator_id", "exact"),
      (28, "Institution", "zs_full_name", "similar", "Immediate Parent Organization Unit Unique Identifier", "exact"),
      (29, "Institution", "zs_full_name", "similar", "zs_ultimate_parent_id", "exact")
    )
      spark.createDataFrame(rulesList).toDF("zs_rule_number", "zs_entity_type", "zs_attribute_1", "zs_match_condition_1", "zs_attribute_2", "zs_match_condition_2")
  }
}