println("###############starting split test###################")
List(5,4).splitAt(-1)
List(5,4).splitAt(0)
List(5,4).splitAt(1) //its 1 based not zero based
List(5,4).splitAt(2)
List(5,4).splitAt(3)
//returns tuple with lists
println("###############starting split test###################")

"madam".zipWithIndex
"madam".zip("madam")
println{"########some list examples start#######"}
1 :: List(2)
List(1,2) :: List(List(3,4))
1 :: List(List(2,3))
1 :: Nil
List('a','b','c') :: Nil //tricky one
List(2,3,4) :: List()
val head :: tail = List(1,2,3,4,5,6)
println{"########some list examples end#######"}

println("###############starting span###################")
Seq(6,2,3,4).span(x => x % 2 == 0)
Seq(1,2,3,4).span(x => x % 2 == 0)

val l = List("abc","","def","hih","")
l.map(_.trim).partition(_.nonEmpty)

println("###############ending span###################")

println("#########concat start ####")
List(1,2,3,4) ++  List(5,6,7,8)
val llll1: List[Int] = List(1,2,3,4) ::: List(5,6,7,8)
val lllll2: Seq[List[Int]] = List(List(1,2,3,4)) ::: List(List(5,6,7,8))
println("#########concat end ####")

println("##finding max using reduceleft start######")
def tr(a:Int, b:Int) = a max b
val res = (1 to 10).reduceLeft{(a,b) => tr(a,b)}
println("##finding max using reduceleft end######")

println("####unzipping start#########")
val uz = List(('a',1),('b',2),('c',3),('d',4))
uz.unzip
println("####unzipping end #########")

println("####drop take start#########")
val ll1 = List()
val lb1 = Nil
ll1.take(2)
lb1.drop(4)
println("####drop take end#########")

println("####List[Nothing] start#########")
val lN1: List[Nothing] = List()
val lN2: List[Nothing] = Nil
val lN3:List[String] = Nil
val lN4:List[Int] = Nil
println("####List[Nothing] end#########")


