package permutcombin

object Permutations1 extends App {
  println("ABC".permutations.toList.mkString("[", ",", "]"))
}
object Combinations1 extends App {
  println("ABC".combinations(1).toList.mkString("[", ",", "]"))
  println("ABC".combinations(2).toList.mkString("[", ",", "]"))
  println("ABC".combinations(3).toList.mkString("[", ",", "]"))
}

object JerinsCom extends App {

  val x = List(6 , 5  ,20 , 7 , 4)
  val perm =  x.permutations.toList
  println(perm)
  println("======")
  println(perm.length)

}

object usingf extends App {
  def permutations[T]: List[T] => Traversable[List[T]] = {
    case Nil => List(Nil)
    case xs => {
      for {
        (x, i) <- xs.zipWithIndex
        ys <- permutations(xs.take(i) ++ xs.drop(1 + i))
      } yield {
        x :: ys
      }
    }
  }
  val x = List(6 , 5  ,20,7,1)
  val r = permutations(x)
  println(r)
  println(r.toList.length)
}

