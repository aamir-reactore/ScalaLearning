package old.implicits

object IntOps {
  implicit class ExtendedInt(n: Int) {

    def times(fn: Unit => Unit) = {
      (0 until n).foreach(x => fn)
    }
  }
}


object IntExtentions extends App {

    import IntOps._
    5.times { _ =>
      println("Hello World!")
    }
}
