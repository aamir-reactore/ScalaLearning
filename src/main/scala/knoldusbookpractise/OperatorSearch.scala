package knoldusbookpractise

object OperatorSearch extends App {

  // val l = List("-", "*", "/", "+")
   val l = List("-", "*", "/")

   val sameCombo = l map {x =>
     List(x, x)
   }

   val res  = l.permutations ++ sameCombo foreach {x =>
     println(x)
   }

  println()

}

