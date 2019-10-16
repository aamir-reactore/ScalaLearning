package collections.map

object KnoldusMap1 extends App {

  val x = Map(1 -> 2)
  x(1) //calls apply method, internally get
  x.apply(1) //calls apply method, internally get

  println(x)
  val res = x.updated(1, 12)
  println(res)

}