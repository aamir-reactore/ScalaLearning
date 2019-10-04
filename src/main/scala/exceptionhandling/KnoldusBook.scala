package exceptionhandling

import scala.util.control.NonFatal

object Program1 extends App {

  scala.util.Try(12 / 0) match {
    case scala.util.Success(s)  => println("operation successful")
    case scala.util.Failure(ex) => {
      println("error occured")
      println(ex.getMessage)
    }
  }
}

object Program2 extends App {


  def divide(num: Int, denom: Int): Either[String, Int] = {
    if (denom == 0)
      scala.util.Left("denominator can't be zero")
    else
      scala.util.Right(num / denom)
  }
  println(divide(21, 0))
  println(divide(4, 2))

  val result1: Either[String, Int] = divide(12, 6)
  println(result1.isLeft)
  println(result1.isRight)
  println(result1.left.map(_ + 10))

  println("----------------------------------------------")
  val result2: Either[String, Int] = divide(12, 0)
  println(result2.isLeft)
  println(result2.isRight)

  println("*************************************************")

  def printResults(result: Either[String, Int]) = result match {
    case Left(errorMsg) => println("Error: " + errorMsg)
    case Right(value) => println("Value after division is: " + value)
  }

  println(divide(4, 0))
  println(divide(4, 2))

  try {

  } catch {
    case scala.util.control.NonFatal(ex)             => {
      //preferred over _
    }
    case e if NonFatal(e) => println(e, "Something not that bad.")

  }
}