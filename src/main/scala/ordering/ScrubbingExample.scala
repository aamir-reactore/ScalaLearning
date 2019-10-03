package ordering

object ScrubberTest extends App {

  case class ScrubberRules(sourceAttribute: String, priority: Int, filterCondition: String,
                           arguments: Option[String], filterKeyword: String, targetAttribute: String,
                           targetAttributeValue: String) extends Ordered[ScrubberRules] {
    override def compare(that: ScrubberRules): Int = priority.compareTo(that.priority)
  }

  val genericScrubberRules: List[ScrubberRules] = {
    val scrubberRule1 = ScrubberRules(sourceAttribute = "zs_full_name", priority = 1, filterCondition = "contains", arguments = None,
      filterKeyword = "bond", targetAttribute = "zs_entity_sub",
      targetAttributeValue = "fund")

    val scrubberRule2 = ScrubberRules(sourceAttribute = "zs_full_name", priority = 2, filterCondition = "contains", arguments = None,
      filterKeyword = "bonds", targetAttribute = "zs_entity_sub",
      targetAttributeValue = "fund")

    val scrubberRule3 = ScrubberRules(sourceAttribute = "zs_Industry Classification Code (SIC)", priority = 1, filterCondition = "equals", arguments = None,
      filterKeyword = "6722", targetAttribute = "zs_entity_sub",
      targetAttributeValue = "fund")

    List(scrubberRule1, scrubberRule2, scrubberRule3)
  }

  val colName = "zs_full_name"
  val passedColScrubberRules = genericScrubberRules.filter(_.sourceAttribute == colName).min
  println(passedColScrubberRules)

}