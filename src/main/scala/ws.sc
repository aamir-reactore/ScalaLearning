val x = 1  #:: 2  #:: Stream.empty
x.tail



def primeStream(s: Stream[Int]): Stream[Int] =
  Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
val primes = primeStream(Stream.from(2))



"madam".zip("hello")
def tr(a:Int, b:Int) = if(a > b) a else b
val res = (1 to 10).reduceLeft{(a,b) => tr(a,b)}

val l = List(6,5)
val n = l.length / 2
val r = l splitAt(n)

