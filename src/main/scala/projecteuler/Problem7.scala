package projecteuler

object NumberPrimeNumberMax extends App {

  def checkPrime(num: Int): Boolean = {
    !(2L to Math.sqrt(num).toInt).exists(num % _ == 0)
  }

  def primeIterator(num: Int): Int = {
    Iterator.from(num + 1).find(checkPrime).get
  }

  val primeList: Iterator[Int] = Iterator.iterate(2)(primeIterator)
  val res = primeList.take(10001).toList.max
  println(res)

}

/**
  * As soon as Iterator.find() matches the condition it instantly returns without continuing other Iterator elements else keeps on iterating.
  * Please check Implementation of Iterator.find in trait Iterator and plz check Iterator.iterate implementation also.
  * We can find object-private implementation of variables inside Iterator.iterate method.
  */
