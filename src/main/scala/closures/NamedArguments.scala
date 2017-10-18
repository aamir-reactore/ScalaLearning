package closures

object NamedArguments1 extends App {
  def speed(rate:Long,distance:Float, time:Float) = {
    print(s"distance = $distance,")
    print(s"time = $time,")
    print(s"rate = $rate \n")
  }
  speed(100,200,40)
  // speed(time = 200, distance = 100,40) CTE,positional after named argument
     speed(40,time=200,distance=100)
}