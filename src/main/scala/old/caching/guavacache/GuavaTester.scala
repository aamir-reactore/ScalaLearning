/*
package old.caching.guavacache

import scala.concurrent.duration._
import scalacache.memoization._
case class Employee(name:String,dept:String,empId:String)
import GuavaCacheImpl._

object GuavaTest extends App with GuavaCacheManagerComponent  {

  //on first invocation, cache will be populated with corresponding employee record

   println(getFromDatabase("100"))
   println(getFromDatabase("103"))
   println(getFromDatabase("110"))

  println("##########Now no database hit message, values comming from old.caching.guava cache#############")
  println(getFromDatabase("100"))
  println(getFromDatabase("103"))
  println(getFromDatabase("110"))

  println(underlyingGuavaCache.size)

  //expensive database call
  def getFromDatabase(empId:String): Employee = memoizeSync(Some(1.minute)) {
    val e1 =   Employee("Mahesh", "Finance", "100")
    val e2 =   Employee("Rohan", "IT", "103")
    val e3 =   Employee("Sohan", "Admin", "110")

    val database = Map("100" -> e1, "103" -> e2, "110" -> e3)

    println(s"Database hit for $empId")

     database(empId)

  }

  //async version of memoization
  //import scala.concurrent.Future
  //import scala.concurrent.ExecutionContext.Implicits.global
  /*  def getFromDatabase(empId:String): Future[Employee] = memoize(1.minute) {
      val e1 =   Employee("Mahesh", "Finance", "100")
      val e2 =   Employee("Rohan", "IT", "103")
      val e3 =   Employee("Sohan", "Admin", "110")

      val database = Map("100" -> e1, "103" -> e2, "110" -> e3)

      println(s"Database hit for $empId")

      Future.successful(database(empId))

    }*/
}

object GuavaPutGetTest extends App with GuavaCacheManagerComponent {

  put("abc","1",Employee("jack","psycho","90"))
  val size =  getCacheSize("abc")
  val all = getAll("abc")
  val value = get("abc","1")
  println(size)
  println(all)
  println(value)
}*/
