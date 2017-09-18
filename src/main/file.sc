val dir = "/var/reactore/webapps/folder"

def ll(dir:List[String]) = {
  def r(dir:List[String],acc:String):String = dir match {
    case Nil => acc
    case h :: tail => {
      create(s"$acc/$h")
      r(tail,s"$acc/$h")
    }
  }
  r(dir,"")
}
def create(str:String) = {
  println(s"create $str")
  println("path created")
}
val list = "/var/reactore/webapps/debians/zipfiles"
val x = ll(list.split("/").toList.tail)
