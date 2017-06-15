List(6, 1, 2, 3, 4).partition(x => x % 2 == 0)
List(6, 1, 2, 3, 4).span(x => x % 2 == 0)

val n = 4
val l = List('a', 'b', 'c','d','e','f','g')
val tempList = (l.take(n) ++ l.drop(n+1),l(n))

l.splitAt(2)





