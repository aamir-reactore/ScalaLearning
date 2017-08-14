package martinoderskyvideos.week5


object ListMethods extends App {

  def last[T](xs: List[T]): T = xs match {  // O(n == for each element in list) -- complexity
    case List() => throw new Error("last of empty list")
    case List(x) => x
    case _ :: tail => last(tail)
  }
  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    case List(_) => List()
    case h :: tail => h :: init(tail)
  }
 //List(1) ::: List(2) ==> normal concat, adds List(2) at last of List(1)
  def concat[T](xs:List[T], ys:List[T]):List[T] = xs match { // call on each element of left list of complexity = O(length of left list)
    case List()  => ys
    case z :: zs => z :: concat(zs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match { //O(proportional to the size of list)
    case List()  => xs
    case y :: ys => reverse(ys) ++ List(y)
  }
}