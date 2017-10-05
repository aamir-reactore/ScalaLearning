package eciffocodeexamples.csvfilewriter

object DirectoryStructure1 extends App {

  def buildPaths(dir: List[String]) = {
    def recursiveBuild(dir: List[String], acc: String): String = dir match {
      case Nil => acc
      case h :: tail => {
        create(s"$acc/$h")
        recursiveBuild(tail, s"$acc/$h")
      }
    }
    recursiveBuild(dir, "")
  }

  def create(str: String) = {
    println(s"create $str")
    println("path created")
  }

  val list = "/var/reactore/webapps/debians/zipfiles"
  val x = buildPaths(list.split("/").toList.tail)
  println(s"created structure x")
}