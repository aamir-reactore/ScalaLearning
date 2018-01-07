//
package bobrockets
package navigation {
  private[bobrockets] class Navigator {

    protected[navigation] def useStartChart = { }
    class LegOfJourney {
      private[Navigator] val distance = 20
      (new Navigator).name // inner class can access outer class private members
    }
    private[this] val speed = 40
    private  val name = ""

    def increaseSpeed(that:Navigator) =  this.speed //+ that.speed, CTE speed is object private access

    (new LegOfJourney).distance
  }
}

package launch {
  import navigation._
  object Vehicle {
    val guide = new Navigator
    //guide.useStartChart, CTE useStartChart protected to Navigator
    //guide.ditance, CTE distance is private to Navigator
  }
}
/**
  -> no access modifier means public access
  -> private[bobrockets] means access within outer package
  -> private[navigation] means access with inner package
  ->  private[LegOfJourney], sort of inner class private and so inaccessible to outer class
  */