List(6, 1, 2, 3, 4).partition(x => x % 2 == 0)
List(6, 1, 2, 3, 4).span(x => x % 2 == 0)

List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k').slice(3,7)
List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k').zip(1 to 7).filter(_._2 > 3).unzip._1

val l = List(1,2,3,4)
l :+ 5
List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k').splitAt(9)



