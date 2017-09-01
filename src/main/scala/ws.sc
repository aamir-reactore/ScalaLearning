import org.joda.time.{LocalTime, Minutes, Period}

val x = 1  #:: 2  #:: Stream.empty
x.tail



def primeStream(s: Stream[Int]): Stream[Int] =
  Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
val primes = primeStream(Stream.from(2))



"madam".zip("hello")
def tr(a:Int, b:Int) = if(a > b) a else b
val res = (1 to 10).reduceLeft{(a,b) => tr(a,b)}


val list = List(1, 2, 3, 4)
list.foreach(println(_))
list.foreach(x => println(x + "test"))

val numList1 = List(1,2)
val numList2 = List(3,4)

for {
   first <- numList1
  second <- numList2
} yield first + second

List('a', 'b', 'c', 'd', 'e', 'f', 'g').splitAt(-2)

val time1 = LocalTime.parse("08:20:00.000")
val time2 = LocalTime.parse("10:00:00.000")
val timeDiff = Minutes.minutesBetween(time1, time2).getMinutes * 60000
val period = new Period(timeDiff)
s"Hours-${period.getHours} Minutes-${period.getMinutes} Seconds-${period.getSeconds}"

Seq(6,2,3,4).span(x => x % 2 == 0)
Seq(1,2,3,4).span(x => x % 2 == 0)
