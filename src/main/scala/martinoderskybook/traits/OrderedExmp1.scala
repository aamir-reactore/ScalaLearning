package martinoderskybook.traits


object OrderedExmp1 extends App {
  val fooList = List(Foo(6), Foo(3), Foo(5), Foo(4))
  println(fooList.sorted)
}

case class Foo(id: Int) extends Ordered[Foo] {
  override def compare(that: Foo): Int = that.id match {
    case x if x < id => 1
    case x if x > id => -1
    case _           => 0
  }
}

class People(var name: String) extends Ordered[People] {
  override def toString: String = name

  // return 0 if the same, negative if this < that, positive if this > that
  /*  def compare (that: People) = {
      if (this.name == that.name)
        0
      else if (this.name > that.name)
        1
      else
        1.unary_-
    }*/
  def compare(that: People) = {
    name.compareTo(that.name)
  }
}

object PersonOrdTest extends App {
  val p1 = new People("aamir")
  val p2 = new People("obaid")
  val p3 = new People("jess")
  val p4 = new People("kimberly")

  val l = List(p1, p2, p3, p4)
  println(l.sorted)
}