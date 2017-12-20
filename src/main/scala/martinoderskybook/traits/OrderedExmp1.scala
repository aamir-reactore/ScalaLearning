package martinoderskybook.traits


object OrderedExmp1 extends App {
val fooList = List(Foo(6),Foo(3),Foo(5),Foo(4))
  println(fooList.sorted)
}

case class Foo(id: Int) extends Ordered[Foo] {
  override def compare(that: Foo): Int = that.id match {
    case x if x < id => 1
    case x if x > id => -1
    case _           => 0
  }
}