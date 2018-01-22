val product = (x: Int, y: Int) => {
  val result = x * y
  println(s"multiplied $x by $y to yield $result")
  result
}
val a = Array(2, 3, 4)
a.scanLeft(10)(product)

val l = List(1,2,3,4)
l.scanLeft(2)(_ + _)
//will keep seed in list
//res1 = seed + 1(first element of list)
// then res1 and 2(from list)