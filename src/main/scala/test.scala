import java.util.Scanner

object test extends App {
  val a = Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9))
  val b  =  Array(Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9))
  val c = Array.ofDim[Int](3, 3)

  for (i <- 0 until 3; j <- 0 until 3; k <- 0 until 3) {
    c(i)(j) = c(i)(j) + a(i)(k) * b(k)(j)
  }
  for (i <- 0 until 3) {
    for (j <- 0 until 3) {
     print(c(i)(j) + " ")
    }
    println()
  }
}
