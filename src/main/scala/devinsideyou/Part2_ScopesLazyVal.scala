package devinsideyou

object Part2a extends App {

    val line = {
      println("I am at line 6")
      1224
    }

  println(s"First time: $line")
  println(s"Second time: $line")
}

object Part2b extends App {

  def line = {
    println("I am at line 6")
    1224
  }

  println(s"First time: $line")
  println(s"Second time: $line")
}

object Part2c extends App {

  lazy val line = {
    println("I am at line 6")
    1224
  }

  println(s"First time: $line")
  println(s"Second time: $line")
}