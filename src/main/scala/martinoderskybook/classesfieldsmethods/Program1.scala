package martinoderskybook.classesfieldsmethods

object Program1 extends App {

  val acc = new CheckSumAccumulator
  val csa = new CheckSumAccumulator

  val x = 3
  val y = 4
  val s = x //two statements
  +y
  val q = (x // one statement
    + y)
  val r =  x + // one statement
  y
  println(s)
  println(q)
  println(r)
}

