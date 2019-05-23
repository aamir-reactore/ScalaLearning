package InterviewQuestions

object Option1 extends App {

  val l = List(("Joe", Some(40)), ("Smith", Some(60)), ("David", Some(20)), ("Nathan", None), ("Zac", Some(12)), ("Elizebth", Some(14)))

  val r = l.filter(_._2.isDefined).map(x => (x._1, x._2.get)).filter(_._2 > 15).map(_._2).sum

  println(r)
}