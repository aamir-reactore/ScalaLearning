package exceptions

trait MyException extends Exception {
  def exception:Throwable
  def message:String

  override lazy val getMessage: String    = message
  override lazy val getCause  : Throwable = exception
}
case class GenericException(errorCode:String = "1001", override val message:String, override val exception:Throwable) extends MyException
