package officecodeexamples.Maps

import scala.collection.immutable

case class QuestionsOptionsMapping(questions: String, options: List[String])

abstract class BlastingApi {

  def getPreQuestions: List[QuestionsOptionsMapping] = {
    val preQuestions = QuestionConstants.questions.filterKeys(_.startsWith("pre"))
    val preQuestionsOptions = QuestionOptions.questionOptions.filterKeys(_.startsWith("pre"))
    val res: immutable.Iterable[QuestionsOptionsMapping] = preQuestions map { case (key, value) =>
      val option = preQuestionsOptions.find(_._1 == key).map(_._2).getOrElse(List("No Options Defined"))
      QuestionsOptionsMapping(value, option)
    }
    res.toList
  }

  def getPostQuestions = {
    val preQuestions = QuestionConstants.questions.filterKeys(_.startsWith("post"))
    val preQuestionsOptions = QuestionOptions.questionOptions.filterKeys(_.startsWith("post"))
    val res: immutable.Iterable[QuestionsOptionsMapping] = preQuestions map { case (key, value) =>
      val option = preQuestionsOptions.find(_._1 == key).map(_._2).getOrElse(List("No Options Defined"))
      QuestionsOptionsMapping(value, option)
    }
    res.toList
  }
}

object ImplBlastingApi extends BlastingApi


object QuestionConstants {

  val q1 = "Are all personnel evacuated?"
  val q2 = "Is all Equipment and Machinery evacuated?"
  val q3 = "Is remaining explosives and accessories removed from blast site?"
  val q4 = "Have all trunk line connections been checked?"
  val q5 = "Have the blasters and assistance taken suitable shelter?"
  val q6 = "Are all personnel evacuated?"
  val q7 = "Number of Misfire?"
  val q8 = "Nature of Ground Vibration?"
  val q9 = "Fragmentation?"

  val questions = Map("preq1" -> q1, "preq2" -> q2, "preq3" -> q3, "preq4" -> q4,
    "preq5" -> q5, "postq1" -> q6, "postq2" -> q7, "postq3" -> q8, "postq4" -> q9,"postq5" -> "abc")

}

object QuestionOptions {

  val q1 = "Are all personnel evacuated?"
  val q2 = "Is all Equipment and Machinery evacuated?"
  val q3 = "Is remaining explosives and accessories removed from blast site?"
  val q4 = "Have all trunk line connections been checked?"
  val q5 = "Have the blasters and assistance taken suitable shelter?"
  val q6 = "Are all personnel evacuated?"
  val q7 = "Number of Misfire?"
  val q8 = "Nature of Ground Vibration?"
  val q9 = "Fragmentation?"

  val questionOptions = Map("preq1" -> List("Yes"), "preq2" -> List("Yes"), "preq3" -> List("Yes"), "preq4" -> List("Yes"),
    "preq5" -> List("Yes"), "postq1" -> List("Yes", "No"), "postq2" -> List("Yes", "No"), "postq3" -> List("Severe", "Not Severe"), "postq4" -> List("Good", "Moderate", "Poor"))

}