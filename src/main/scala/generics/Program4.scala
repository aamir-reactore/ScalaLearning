package generics


object AttemptTest extends App {
  println(Attempt[Int](11./(0)))
  val l = 1 :: List[Nothing]()
}

sealed trait Attempt[A]

final case class Failed[A](exception: Throwable) extends Attempt[A]

final case class Succeeded[A](value: A) extends Attempt[A]

object Attempt {
  def apply[A](f: => A): Attempt[A] = {
    try {
      val result: A = f
      Succeeded(result)
    } catch {
      case e: Exception => Failed(e)
    }
  }
}