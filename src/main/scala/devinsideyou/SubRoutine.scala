package devinsideyou

object FarAway1 extends App {

  def code: (Boolean, Boolean) => Unit = {

    (bol1, bol2) =>

      def result =
        if (bol1) {
          "I am feeling happy today"
        } else if (bol2) {
          "I am feeling sad today"
        } else {
          "Not sure how I am feeling today"
        }

      println(result)


  }

  val isSunny = true
  val isRainyDay = false
  val r = code(isSunny, isRainyDay)
}

object FarAway2 extends App {

  def code: (Boolean, Boolean) => Unit = {

    (bol1, bol2) =>

      println(
        if (bol1) {
          "I am feeling happy today"
        } else if (bol2) {
          "I am feeling sad today"
        } else {
          "Not sure how I am feeling today"
        }

      )


  }

  val isSunny = true
  val isRainyDay = false
  val r = code(isSunny, isRainyDay)
}

object FarAway3 extends App {

  def code = {

    println("I'm about to create a sub routine....")
    (bol1: Boolean, bol2: Boolean) => {
      if (bol1) {
        "I am feeling happy today"
      } else if (bol2) {
        "I am feeling sad today"
      } else {
        "Not sure how I am feeling today"
      }

    }

  }

  val isSunny = true
  val isRainyDay = false
  println(code(isSunny, isRainyDay))
  println(code(isRainyDay, isSunny))

  println("-" * 40)

  val frozenCode: (Boolean, Boolean) => String = code
  println(frozenCode(isSunny, isRainyDay))
  println(frozenCode(isRainyDay, isSunny))
}


