import scala.collection.immutable.Stack

val ints = Stack[Int](0)
val r1 = ints.push(1)
val r2 = r1.push(2)
val r3 = r2.push(3)
val r4 = r3.push(4)

val r5 = r4.pop
r5.top

val s = Stack(1,2,3)
