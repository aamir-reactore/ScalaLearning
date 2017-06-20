package projecteuler

object Problem7Sol extends App {

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