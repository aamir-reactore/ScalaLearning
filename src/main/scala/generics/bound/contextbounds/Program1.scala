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

object ContextBounds3 extends App {


  def foo[T : ({type L[X] = X => Int})#L](x: T): T = x

  //val res: Int = foo("22") cte, define string to int implicit

  val res:Int = foo(20)
  println(res)

}

object ContextBounds4 extends App {


  def foo[E, T : ({type L[X] = X => E})#L](x: T): E = x

  //val res: Int = foo("22") cte, define string to int implicit

  val res:Int = foo(20)
  println(res)

}

object ContextBounds5 extends App {


  def foo[T](x: T)(implicit eval: T => Int): T = x

  implicit def sToInt(s: String) = s.toInt
  val res: Int = foo(22)

  println(res)
}

object ContextBounds6 extends App {


  def foo[T, E](x: T)(implicit eval: T => E): E = x

  implicit def sToInt(s: String) = s.toInt
  val res: Int = foo(202)

  println(res)
}




