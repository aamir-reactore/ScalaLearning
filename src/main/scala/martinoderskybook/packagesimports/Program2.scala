package bobrockets {

  package navigation {
    class Navigator2 {
      val map = new StarMap
    }
    class StarMap
  }
  class Ship1 {
    val nav = new navigation.Navigator2
  }
  package fleets {
    class Fleet1 {
      def addShip = new Ship1
    }
  }
}