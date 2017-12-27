
def primeStream(s: Stream[Int]): Stream[Int] =
  Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
val primes = primeStream(Stream.from(2))


println("##finding max using reduceleft start######")
def tr(a:Int, b:Int) = a max b
val res = (1 to 10).reduceLeft{(a,b) => tr(a,b)}
println("##finding max using reduceleft end######")



/*val time1 = LocalTime.parse("08:20:00.000")
val time2 = LocalTime.parse("10:00:00.000")
val timeDiff = Minutes.minutesBetween(time1, time2).getMinutes * 60000
val period = new Period(timeDiff)
s"Hours-${period.getHours} Minutes-${period.getMinutes} Seconds-${period.getSeconds}"*/

println("###############starting span###################")
Seq(6,2,3,4).span(x => x % 2 == 0)
Seq(1,2,3,4).span(x => x % 2 == 0)
println("###############ending span###################")
val l = List("abc","","def","hih","")
l.map(_.trim).partition(_.nonEmpty)

println("###pattern match test start")
val num = 2
num match {
  case x if x == 1 => println("one, a lonely number")
  case y if y == 2 || y == 3 => println(y)
  case _ => println("some other value")
}
println("###pattern match test end")
List(1,2,3,4) ++  List(5,6,7,8)
val ll1: List[Int] = List(1,2,3,4) ::: List(5,6,7,8)
val ll2: Seq[List[Int]] = List(List(1,2,3,4)) ::: List(List(5,6,7,8))


println("###############starting split test###################")
List(5,4).splitAt(-1)
List(5,4).splitAt(0)
List(5,4).splitAt(1)
List(5,4).splitAt(2)
List(5,4).splitAt(3)
println("###############starting split test###################")

"madam".zipWithIndex
"madam".zip("madam")
println{"########some list loopholes start#######"}
1 :: List(2)
List(1,2) :: List(List(3,4))
1 :: Nil
List('a','b','c') :: Nil

println{"########some list loopholes end#######"}