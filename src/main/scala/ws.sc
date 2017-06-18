List(6, 1, 2, 3, 4).partition(x => x % 2 == 0)
List(6, 1, 2, 3, 4).span(x => x % 2 == 0)

List.fill(4)('a')
val s = Stream.from(4)
s.takeWhile(_ < 10).foreach(println)



val str = """richardcore.util.BigNum("0")"""
//val l = str.replace(str.substring(str.indexOf("scala"),str.indexOf("(")).trim,"Double").replace("\"","")

val r = """^(.*?)core.util.BigNum.*?"(\d+)".*$""".r
val b = r.replaceAllIn(str,"Int")






