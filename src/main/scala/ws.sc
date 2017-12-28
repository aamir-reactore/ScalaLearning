import org.joda.time.{LocalTime, Minutes, Period}



println("###pattern match test start")
val num = 2
num match {
  case x if x == 1 => println("one, a lonely number")
  case y if y == 2 || y == 3 => println(y)
  case _ => println("some other value")
}
println("###pattern match test end")



