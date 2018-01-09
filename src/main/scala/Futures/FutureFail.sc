
case class ProcessFailed(message:Option[Throwable] = None)
case class ProcessedSuccessFully(liters:Long)

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

val f = Future[Any] {
  ProcessFailed(Some(new Exception("Gone for dogs!")))
  //ProcessedSuccessFully(100L)
}
val g = Future[Any] {
  ProcessedSuccessFully(100L)
}
val h = Future[Any] {
  ProcessedSuccessFully(100L)
}
val fTransformed1: Future[String] = f.map {
  case ProcessFailed(ex) => throw ex.getOrElse(new Exception("gone!"))
  case _ => "Success"
}
val fTransformed2: Future[String] = g.map {
  case ProcessFailed(ex) => throw ex.getOrElse(new Exception("gone!"))
  case _ => "Success"
}
val fTransformed3: Future[String] = h.map {
  case ProcessFailed(ex) => throw ex.getOrElse(new Exception("gone!"))
  case _ => "Success"
}
val sequenced1 = Future.sequence(List(fTransformed1,fTransformed2))
//Await.result(sequenced1,1.seconds)
//both will fail
val sequenced2 = Future.sequence(List(fTransformed2,fTransformed3))
Await.result(sequenced2,1.seconds)
