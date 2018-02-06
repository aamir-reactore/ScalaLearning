package ordering

import ordering.OrderingTest2.BoxOrdering


case class Value(i: Int) extends Ordered[Value] {
  def compare(that: Value) = this.i - that.i
}

object OrderingTest1 extends App {
  val value1 = Value(2)
  val value2 = Value(4)

  println(value1 compare value2)
  println(value2 compare value1)

  val valueList = List(Value(5), Value(1), Value(3), Value(-2), Value(-6))
  println(valueList.sorted)
}

object OrderingTest2 extends App {

  /**
    * Ordering can be thought as a more general interface then Ordered.
    * you can have many Orderings available for a class, which for Ordered isn’t possible without subtyping
    **/
  trait Box[T] {
    def value: T
  }

  case class IntBox(value: Int) extends Box[Int]

  class Sort[T](ordering: Ordering[Box[T]]) {
    def apply(boxes: Seq[Box[T]]) = {
      boxes.sorted(ordering)
    }
  }

  class BoxOrdering[T](ordering: Ordering[T]) extends Ordering[Box[T]] {
    def compare(x: Box[T], y: Box[T]): Int = ordering.compare(x.value, y.value)
  }

  val valueList = List(IntBox(5), IntBox(1), IntBox(3), IntBox(-2), IntBox(-6))
  val boxOrdering: Ordering[Box[Int]] = new BoxOrdering(scala.math.Ordering.Int)
  val sort = new Sort(boxOrdering)
  println(sort(valueList))
}

object BoxSort {

  import ordering.OrderingTest2.Box

  def apply[T](boxes: Seq[Box[T]])(implicit ordering: Ordering[T]) = {
    val boxOrdering = new BoxOrdering(ordering)
    boxes.sorted(boxOrdering)
  }
}

object OrderingTest3 extends App {
  import ordering.OrderingTest2.IntBox
  val valueList = List(IntBox(5), IntBox(1), IntBox(3), IntBox(-2), IntBox(-6))
  //println( BoxSort(valueList)(scala.math.Ordering.Int))
  println(BoxSort(valueList))
}
