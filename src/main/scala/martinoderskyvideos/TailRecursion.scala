package martinoderskyvideos

object TailRecursionExample extends App {

  def gcd(a:Int, b:Int):Int = if(b == 0) a else gcd(b,a % b)
  def factorial(num:Int):Int ={
    def fact(num:Int, acc:Int):Int = {
      if(num == 0) acc else fact(num - 1,num * acc)
    }
    fact(num, 1)
  }
  println(s"gcd of 18, 36 is ${gcd(36,18)}")
  println(s"factorial of 6 is ${factorial(6)}")
}
