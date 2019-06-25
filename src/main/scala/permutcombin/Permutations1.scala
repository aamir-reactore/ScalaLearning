package permutcombin

object Permutations1 extends App {
  println("ABC".permutations.toList.mkString("[", ",", "]"))
}
object Combinations1 extends App {
  println("ABC".combinations(1).toList.mkString("[", ",", "]"))
  println("ABC".combinations(2).toList.mkString("[", ",", "]"))
  println("ABC".combinations(3).toList.mkString("[", ",", "]"))
}