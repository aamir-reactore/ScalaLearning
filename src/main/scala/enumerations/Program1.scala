package enumerations

object Main extends Enumeration {
  type Main = Value

  // Assigning Values
  val first = Value("Thriller") // ID = 0
  val second = Value("Horror") // ID = 1
  val third = Value("Comedy") // ID = 2
  val fourth = Value("Romance") // ID = 3

  // Main Method
  def main(args: Array[String]) {
    println(s"ID of third = ${Main.third.id}")
  }
}

object Program1 extends App {

  import Main._

  println(s"Main Movie Genres = ${Main.values}")
  println(s"The third value = ${Main.third}")
  println(s"ID of third = ${Main.third.id}")

  Main.values.foreach
  {
    // Matching values in Enumeration
    case d if d == Main.third =>
      println(s"Favourite type of Movie = $d")
    case _ => None
  }



}

object jj extends App {

  object Episode extends Enumeration {
    val NEWHOPE, EMPIRE, JEDI = Value
  }

  def abc(x: Episode.Value) = {
    println(x)
  }
  val x = Option(Episode.EMPIRE)
  trait Character {
  def id: String
  def name: String
}
  case class Human(id:String, name:String) extends Character
  case class Droid(id:String, name:String) extends Character
  val c1 = Human("1", "aamir")
  val c2 = Droid("1", "obaid")
  val humans = List(c1)
  val droids = List(c2)

  def getHero(episode: Option[Episode.Value]) =
    {
      episode flatMap (_ ⇒ getHuman("1000")) getOrElse droids.last
    }

  def getHuman(id: String): Option[Human] = humans.find(c ⇒ c.id == id)

  def getDroid(id: String): Option[Droid] = droids.find(c ⇒ c.id == id)
  //x map {_}
  abc(Episode.EMPIRE)
  println(getHero(Some(Episode.EMPIRE)))
}