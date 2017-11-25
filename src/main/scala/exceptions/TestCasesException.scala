/*
package exceptions

import org.scalatest.{Matchers, Tag, WordSpec}

import scala.util.Try

class MyExceptionMessageSpec extends  Matchers {
  "My Exception Message spec" should {
    "return the message in the exception when getMessage is called " in {
      val res = throwExceptionMethod
      res.isFailure shouldBe true
      res.failed.get.getMessage shouldBe "My Error Message"
      res.failed.get.getCause shouldBe an [MyCauseException]
      res.failed.get.getCause.getMessage shouldBe "----"
    }
  }

  def throwExceptionMethod: Try[Unit] = {
    val genericException = GenericException(message = "My Error Message", exception = new MyCauseException("----"))
    Try {
      throw genericException
    }
  }
}
class MyCauseException(msg:String) extends Exception(msg)
*/