package projecteuler

object Problem5Sol extends App {

  def checkDiv(num: Long): Boolean = {
    (20 to 11 by -1).forall(num % _ == 0)
  }
  val res = Iterator.from(1).find(checkDiv(_))

  println(res.get)

}