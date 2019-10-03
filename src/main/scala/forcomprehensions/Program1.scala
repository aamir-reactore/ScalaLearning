package forcomprehensions

object Program1 extends App {
  val cities = List("Washington", "Los Angeles", "New York", "Charlotte")

  for(city <- cities
  if city != "Washington"
  ) println(city)
}