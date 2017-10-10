package closures

object RepeatedParameters1 extends App {

   def echo(args: String*): Unit = {
     for(arg <- args) print(arg + " ")
     println()
   }
  echo()
  echo("one")
  echo("one","two")
  echo("one","two","three")

  val arr = Array("what's","up","doc?")

  //echo(arr)//CTE
  println("############################")
  echo(arr: _ *)

}