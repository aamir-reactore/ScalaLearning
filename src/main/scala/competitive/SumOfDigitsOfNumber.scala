package competitive

import java.util.Scanner


object SumOfDigitsOfNumber extends App {

  System.out.println("Enter a number:")
  val in = new Scanner(System.in)
  var num = in.nextInt
  var sum = 0
  while ( {
    num > 0
  }) {
    val rem = num % 10
    sum = sum + rem
    num = num / 10
  }
  System.out.println("Sum is :" + sum)

}