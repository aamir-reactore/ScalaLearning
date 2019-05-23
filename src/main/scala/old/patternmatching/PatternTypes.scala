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

  Pi match {
    case Pi => println(s"Strange math? Pi = $Pi")
    case _  => println("OK")
  }

  //small case treated as variable pattern
  E match {
    case pi => println(s"Strange math? Pi = $pi")
    case _  => println("OK")
  }

  E match {
    //capital case treated as constant pattern
    case Pi => println(s"Strange math? Pi = $Pi")
    case e  => println(s"matches e coz variable pattern, e = $e")
    case _  => println("OK")
  }
}

object VariableVSConstantPattern2 extends App {

  import math.{Pi => pi}
  import math.E

  E match {
    case `pi` => "Strange math? Pi" + pi
    case _    => "OK"
  }
}

object ConstructorPattern extends App {
  val expr = UnOp("-", UnOp("-", Number(2))) // -(-2)
  //supports nested matches or patterns
  expr match {
    case UnOp("-", UnOp("-", Number(2))) => println("matched")
    case UnOp("-", _)                    => println("matched skipped one")
    case _                               => println("not matched")
  }

  expr match {
    case UnOp("-", _)                    => println("matched skipped one")
    case UnOp("-", UnOp("-", Number(2))) => println("matched")
    case _                               => println("not matched")
  }
}

object SequencePatterns extends App {
  val l1 = List(1, 2, 3, 4, 5, 6)

  l1 match {
    case List(1, _*) => println("list first element as 1 then followed by n elements")
    case _           => println {
      "default case"
    }
  }

  val l2 = List(1, 2, 3, 4, 5, 6)
  l2 match {
    case List(0, _*) => println("list first element as 1 then followed by n elements")
    case _           => println {
      "default case"
    }
  }

}

object TuplePattern extends App {

  def tupleDemo(expr: Any) = expr match {
    case (a, b)    => println(s"matched $a $b")
    case (a, b, c) => println(s"matched $a $b$c")
    case _         => println("default case")
  }

  tupleDemo(("a", 3, "-tuple"))
}

