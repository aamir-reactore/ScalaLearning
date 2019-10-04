package collections.arrays

object Program1 extends App {

  val matrix = Array.ofDim[Int](2,2)
  matrix(0)(0) = 5
  matrix(0)(1) = 4
  matrix(1)(0) = 2
  matrix(1)(1) = 8

  matrix foreach { row => row foreach (x => print(x + " ")); println }
}