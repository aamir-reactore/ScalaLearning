import java.io.PrintWriter

def filesHere = new java.io.File(".").listFiles

//using function literal
def filesMatching1(query:String,
                  matcher: (String,String) => Boolean) = {
  for(file <- filesHere; if matcher(file.getName, query)
   ) yield file
}
filesMatching1(".scala",(x:String,y:String) => x.endsWith(y))
filesMatching1(".scala",(x,y) => x.endsWith(y))
filesMatching1(".scala",_.endsWith(_))

filesMatching1(".scala",_.contains(_))
filesMatching1(".scala",_.matches(_))

// now using closures.
def filesMatching2(matcher: String => Boolean) = {
  for(file <- filesHere; if matcher(file.getName)
  ) yield file
}
val query = ".scala"
filesMatching2((x:String) => x.endsWith(query))
filesMatching2(x => x.endsWith(query))
filesMatching2(_.endsWith(query))

/*****currying*****/
def curriedSum(x: Int)(y: Int) = x + y
val x: Int => Int = curriedSum(1)
val y: Int => Int = curriedSum(2)_  //way to get an actual reference to curriedSum’s “second” function, not space here with _
x(2)
y(2)

println {"hello"}
def testCurly(a:Int,b:Int) = true
//testCurly{1,2} CTE use () for more than 1 args else {} for 1 arg


def twice(op: Double => Double, x: Double) = op(op(x))
twice(_ + 2, 10)

/**loan-pattern**/
def withPrintWriter(file:java.io.File)(op:PrintWriter => Unit) = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close
  }
}
withPrintWriter(new java.io.File("/home/administrator/crudfixLPZK")) {
  writer => writer.println("")
}