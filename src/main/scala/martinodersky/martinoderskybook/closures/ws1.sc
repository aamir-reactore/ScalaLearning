val fl1=(x: Int) => x + 1
fl1(2)

val fl2 = (_: Int) + (_: Int)
fl2(2,4)

println("******Partially applied functions******")

def sum(a: Int, b: Int, c: Int) = a + b + c
val a = sum _
val a1: (Int, Int, Int) => Int = sum //syntactic sugar
a1(1,2,1)
a(1,2,3)
a.apply(1, 2, 3)

val b: Int => Int = sum(1, _: Int, 3)
b(4)
//def to f-literal