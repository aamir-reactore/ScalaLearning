package datastructures.recursion

import java.util.Scanner

class TowerOfHanoi {
  def solve(n: Int, start: String, auxiliary: String, end: String): Unit = {
    if (n == 1) System.out.println(start + " -> " + end)
    else {
      solve(n - 1, start, end, auxiliary)
      System.out.println(start + " -> " + end)
      solve(n - 1, auxiliary, start, end)
    }
  }

  def main(args: Array[String]): Unit = {
    val towersOfHanoi = new TowerOfHanoi
    System.out.print("Enter number of discs: ")
    val scanner = new Scanner(System.in)
    val discs = scanner.nextInt
    towersOfHanoi.solve(discs, "A", "B", "C")
  }
}
