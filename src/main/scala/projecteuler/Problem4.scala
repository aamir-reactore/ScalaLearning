package projecteuler

import scala.collection.immutable.Seq

object CheckPalindromeInRange extends App {

  calculatePalindrome

  private def calculatePalindrome: Seq[Int] = {
     for {
      i <- 100 to 999
      j <- i to 999
      if isPalindrome(i * j)
    } yield i * j
  }

  def isPalindrome(num: Int): Boolean = {
    val numStr = num.toString
    numStr.zip(numStr.reverse).forall(x => x._1 == x._2)
  }
 println(s"largest palindrome from three digit number is ==>${calculatePalindrome.max}")
}