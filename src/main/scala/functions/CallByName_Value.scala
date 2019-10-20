package functions

object CallByName_Value1 extends App {
      // Defined function call-by-name
      def ArticleCounts(i: => Int): Unit = {
      //def ArticleCounts(i: => Int): Unit = {
        println("Tanya did articles " +
          " on day one is 1 - Total = " + i)
        println("Tanya did articles "+
          "on day two is 1 - Total = " + i)
        println("Tanya did articles " +
          "on day three is 1 - Total = " + i)
        println("Tanya did articles " +
          "on day four is 1 - Total = " + i)
      }

      var Total = 0

      // calling function
   /*   val res = ArticleCounts {
        Total += 1
        Total
      }
  */
  val x: () => Int = () => {
     Total = Total + 1
     Total
   }
  ArticleCounts(x())
}

object CallByName_Value2 extends App {
    def something(): Int = {
      println("calling something")
      1 // return value
    }

    // Defined function
    def callByName(x: => Int): Unit = {
      println("x1=" + x)
      println("x2=" + x)
    }

    // Calling function
    callByName(something())
}