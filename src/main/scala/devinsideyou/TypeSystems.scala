package devinsideyou

object TypeSystems1 extends App {

  val b = false
  val x: AnyVal = {if(b) true else 10}:AnyVal
  val y: Unit = {}
}