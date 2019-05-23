package exceptions

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

object forcomtest extends App {

  val res = Try {
    println("cool and nice try")
    method1 map {x =>
      println(x)
      shouldNotBeCalled
    }
  }
  if(res.isFailure) {
    println("fail")
    println(res.failed.get)
    throw res.failed.get
  }

  def shouldNotBeCalled = {
    println("everything is cool thatz y i am called")
  }
  def method1: Future[Int] = {
   for {
     result <- method2
     _=println("working fine in method1")
   } yield result
  }
  def method2: Future[Int] = {
    for {
      result <- method3
      _=println("working fine in method2")
    } yield result
  }
  def method3: Future[Int] = {
    for {
      result <- if(false) {
        Future(10)
      } else {
        throw new Exception("should read this message")
      }
    } yield result
  }


  Thread.sleep(2000)
}