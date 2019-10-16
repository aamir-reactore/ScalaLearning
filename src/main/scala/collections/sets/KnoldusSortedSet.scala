package collections.sets

import scala.collection.immutable.SortedSet

object KnoldusSortedSet1 extends App {
  val sortedSet = SortedSet("Bengaluru", "Agra", "Cochin")
  println(sortedSet)

  val x = Map(1 -> 2)
  x(1)

  println(x)
  val res = x.updated(1, 12)
  println(res)
}