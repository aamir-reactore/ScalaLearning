import java.io.PrintWriter

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

def abc(f: () => Boolean) = {

}
abc(() => 5 > 4)
def ghi(f: => Boolean) = {
  if(f) true else false
}
val r = ghi(5 > 4) // not evaluated unless used, by-name

def jkl(f:Boolean) = {

}
jkl(5>4) //evaluated as soon as passed, by-value