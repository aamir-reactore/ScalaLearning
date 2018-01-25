
println("**************repeated parameters******************")
//args is Array of specified type
def echo1(args: String*) = for (arg <- args) println(arg)
echo1()
echo1("abc")
echo1("def","ghi")

val arr = Array("What's", "up", "doc?")
//echo1(arr) CTE
echo1(arr: _*)

def echo2(l:Int,args: String*) = for (arg <- args) println(arg)
echo2(1)
echo2(1,"abc")
echo2(2,"def","ghi")

case class WSRequestCommand( method: String, payload: Option[String], headers:(String,String)*)
WSRequestCommand("POST", Some("payload"), ("apiKey", "aa4608c6-567d-4906-bf11-ab89118f7d60"), ("mineId", "1"))


println("**************Named arguments******************")
def speed(distance: Float,
          time: Float): Float = distance / time
speed(100, 10)
speed(time = 10, distance = 100)
speed(distance = 100, time = 100)
//speed(10, distance = 100), CTE, specify time not distance, as distance first argument.
speed(10, time = 100)
//speed(time = 10,100) , CTE, positional arguments must come first

println("**************Default parameters******************")
def printTime(out: java.io.PrintStream = Console.out,
              divisor: Int = 1) =
  out.println("time = "+ System.currentTimeMillis()/divisor)
printTime(divisor = 1000)
printTime(out = Console.err)
//printTime CTE
printTime()