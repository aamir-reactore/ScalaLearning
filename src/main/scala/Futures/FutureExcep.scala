package Futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object FutureExcepTest1 extends App {

  val r1: Future[Int] = Future(6 / 0)
  r1 onComplete {
    case Success(_) => {
      println("((((((((((success))))))))))")
    }
    case Failure(f) => throw f
  }
  Thread.sleep(10000)
}

object FutureExcepTest2 extends App {

  val r1: Future[Int] = Future(6 / 0) recoverWith {
    case e: ArithmeticException =>
      Future.failed(e)
  }
  r1.onComplete {
    case Success(_) => {
      println("((((((((((success))))))))))")
    }
    case Failure(f) => throw f
  }
  Thread.sleep(10000)
}

object FutureExcepTest3 extends App {

  val r1: Future[Int] = Future(6 / 0) recoverWith {
    case e: ArithmeticException =>
      throw e
  }
  r1.onComplete {
    case Success(_) => {
      println("((((((((((success))))))))))")
    }
    case Failure(f) => throw f
  }
  Thread.sleep(10000)
}

object FutureExcepTest4 extends App {
  val f = Future {
    Int.MaxValue
  }
  val res: Future[Int] = Future(6 / 0) recoverWith {
    case e: ArithmeticException => f
  } // result: Int.MaxValue

  Thread.sleep(10000)
}

object FutureTransformTest5 extends App {

  val res1: Future[String] = Future(6 / 3).transform[String](_ => {
    "String"
  }, f => {
    throw f
  }
  )

  res1.onComplete {
    case Success(s) => {
      println(s"(((((((((($s))))))))))")
    }
    case Failure(f) => throw f
  }
  Thread.sleep(1000)
  val res2: Future[Int] = Future(6 / 0).transform(s => {
    s
  }, f => {
    throw f
  }
  )

  res2.onComplete {
    case Success(s) => {
      println(s"(((((((((($s))))))))))")
    }
    case Failure(f) => throw f
  }

  Thread.sleep(10000)
}