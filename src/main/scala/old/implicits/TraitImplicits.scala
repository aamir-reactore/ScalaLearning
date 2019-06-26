import Implicits.Foo

case class Choo(i: Int)

trait LL {

  implicit def fooToInt(foo: Choo) = foo.i
}

class YY extends LL {
  def add(f: Choo, i: Int) = f + i
}

object jj extends App {
  val ff = Choo(12)
  println(new YY().add(ff, 20))

}