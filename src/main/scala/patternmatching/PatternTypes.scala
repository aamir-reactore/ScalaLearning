package patternmatching

//matches any object whatsoever
object WildCardPattern extends App {
  val expr = BinOp("+", Var("x"), Number(1))

  expr match {
    case BinOp("-", Var("z"), _) => println(s"$expr is a binary operation")
    case _                       => println("this is the default case")
  }
}

//matches only itself
object ConstantPattern extends App {
  def describe(x: Any) = x match {
    case 5       => "five"
    case true    => "truth"
    case "hello" => "hi!"
    case Nil     => "the empty list"
    case _       => "something else"
  }

  println(describe(5))
  println(describe(true))
  println(describe("hello"))
  println(describe(Nil))
  println(describe(List(1, 2, 3, 4)))
}

//like wildcard pattern but binds variable to whatever object is.
object VariablePattern extends App {
  val expr: Any = "some expression"
  expr match {
    case 0             => println("zero")
    case somethingElse => println(s"not zero $somethingElse")
  }
}

//variable pattern v/s constant pattern
object VariableVSConstantPattern1 extends App {

  import math.{E, Pi}


  E match {
    //capital case treated as constant pattern
    case Pi => println(s"Strange math? Pi = $Pi")
    case _  => println("OK")
  }

  /* Pi match {
     case Pi => println(s"Strange math? Pi = $Pi")
     case _  => println("OK")
   }*/

  //small case treated as variable pattern
  E match {
    case pi => println(s"Strange math? Pi = $pi")
    case _  => println("OK")
  }
}

object VariableVSConstantPattern2 extends App {

  import math.{E, Pi}

  //back-ticks treats small case as constant patterns
  def `val` = ???

  "a" match {
    case `a` => println(s"Strange math? Pi = $pi")
    case _   => println("OK")
  }
}