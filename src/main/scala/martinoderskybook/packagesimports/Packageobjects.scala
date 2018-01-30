import martinoderskybook.packagesimports.{Fruit, Fruits}

package object dylansdelights { //compiles to package.class
  def showFruits(fruit:Fruit) = {
    import fruit._
    println(s"${name}s are $color")
  }
}

package martinoderskybook.packagesimports.accessmodifiers {

  object PrintMenu extends App {
   Predef
    import dylansdelights._

    for (fruit <- Fruits.menu)
      showFruits(fruit)
  }

}
