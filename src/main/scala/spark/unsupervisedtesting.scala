import org.apache.spark.sql.SparkSession

object unsupervisedtesting extends App {

  val spark = SparkSession.builder.master("local").appName("learning spark").getOrCreate
  val sc = spark.sparkContext

  def createRuleDataFrame = {
    var zs_rules_framework_df = spark.emptyDataFrame
    val rulesList: List[(Int, String, String, String, String, String)] = List(
      (1, "Institution", "zs_full_name", "similar", "zs_lei", "exact"),
      (2, "Institution", "zs_full_name", "similar", "zs_tax_identifier", "exact"),
      (3, "Institution", "zs_full_name", "similar", "zs_avox", "exact"),
      (4, "Institution", "zs_full_name", "similar", "Exact Same DUNS Identifier", "exact"),
      (5, "Institution", "zs_full_name", "similar", "Exact Same Bloomberg Identifier", "exact"),
      (6, "Institution", "zs_full_name", "similar", "zs_swift_bic", "exact"),
      (7, "Institution", "zs_full_name", "similar", "zs_central_index_key", "exact"),
      (8, "Institution", "zs_full_name", "similar", "zs_date_incorporation", "exact"),
      (9, "Institution", "zs_full_name", "similar", "Date of Formation", "exact"),
      (10, "Institution", "zs_full_name", "similar", "zs_website_url", "exact"),
      (11, "Institution", "zs_full_name", "similar", "zs_sic_code", "exact"),
      (12, "Institution", "zs_full_name", "similar", "zs_nace_code", "exact"),
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
    zs_rules_framework_df = spark.createDataFrame(rulesList).toDF("zs_rule_number", "zs_entity_type", "zs_attribute_1", "zs_match_condition_1", "zs_attribute_2", "zs_match_condition_2")
    zs_rules_framework_df.show(false)
  }
  createRuleDataFrame
}