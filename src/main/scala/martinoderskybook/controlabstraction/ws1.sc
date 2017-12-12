
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
val x = curriedSum(10)


