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
val num = 99
num match {
  case x if x == 1 => println("one, a lonely number")
  case x if (x == 2 || x == 3) => println(x)
  case _ => println("some other value")
}