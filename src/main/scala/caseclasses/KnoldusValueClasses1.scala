package caseclasses

//case class StudentId(id: Int, name:String) extends AnyVal //CTE
case class StudentId(id: Int) extends AnyVal {
  //val x = 10 //not allowed in value classes
  def getId = id
  //override def equals(obj: Any): Boolean = super.equals(obj)// value classes can't redefine equals, hashCode
  override def toString: String = super.toString
}

case class Student(id: String, name: String) {
  override def equals(obj: Any): Boolean = super.equals(obj)
  def x = throw new Exception("")

   val xd: Null = null
}



class A(id: String, name: String) extends Student(id, name)
//class B(id: String, name: String) extends StudentId(10) //we can't extend a value class



