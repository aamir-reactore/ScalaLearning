package generics.bound.contextbounds
//http://jatinpuri.com/2014/03/replace-view-bounds/

object OldViewBounds extends App {

  implicit def g1[T <% Int](x: T) = x
  val result: Int = g1(22)

  implicit def strToInt[T](x: T):Int = x match {
    case x: String => x.toInt
  }

  val x:Int = "22"
}

object ContextBounds1 extends App {

  type L[X] = X => Int

  def foo[T : L](x: T): T = x

  val res: Int = foo(22)

}

object ContextBounds2 extends App {

  type L[X] = X => Int

  def foo[T : L](x: T): T = x

  val res: Int = foo(22)

}



