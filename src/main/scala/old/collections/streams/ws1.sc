Stream.from(1).map(_ + 1).takeWhile(_ < 10)


def primeStream(s: Stream[Int]): Stream[Int] = {
 println("xxx")
  Stream.cons(s.head, primeStream(s.tail filter {
    _ % s.head != 0
  }))
}
val primes = primeStream(Stream.from(2))
primes.take(10).foreach(println)

Stream.cons(1, 6 #:: 7 #:: Stream.empty)

val s = Stream.from(1)
s.tail