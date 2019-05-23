package Futures

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
object jj extends App {

  case class A(nam: String)

  def executeSequentially[R](fs: Seq[() => Future[R]]) = {
    executeFuturesInternal[R](fs, Nil)
  }

  private def executeFuturesInternal[R](fs: Seq[() => Future[R]], results: List[R]): Future[List[R]] = {
    val fnOpt = fs.headOption
    if (fnOpt.nonEmpty) {
      fnOpt.get().flatMap(
        res => executeFuturesInternal(fs.tail, results ++ List(res))
      )
    } else {
      Future.successful(results)
    }
  }

  /*  val x: () => Future[(Int, Unit)] = () => Future {
      (1, println("start-1"));
      Thread.sleep(500);
      (1, println("stop-1"))
    }*/
  val list: List[() => Future[(Int, Unit)]] = List(
    { () => Future {
      (1,println("start-1")); Thread.sleep(500); (1,println("stop-1"))
    }
    }, { () => Future {
      (2,println("start-2")); Thread.sleep(3000); (2,println("stop-2"))
    }
    }, { () => Future {
      (3,println("start-3")); Thread.sleep(500); (3,println("stop-3"))
    }
    }, { () => Future {
      (4,println("start-4")); Thread.sleep(50); (4,println("stop-4"))
    }
    }
  )

  Await.result(executeSequentially[(Int,Unit)](list), Duration.Inf)

}