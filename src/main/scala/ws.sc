import org.joda.time.{LocalTime, Minutes, Period}

def primeStream(s: Stream[Int]): Stream[Int] =
  Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
val primes = primeStream(Stream.from(2))



println("###pattern match test start")
val num = 2
num match {
  case x if x == 1 => println("one, a lonely number")
  case y if y == 2 || y == 3 => println(y)
  case _ => println("some other value")
}
println("###pattern match test end")



