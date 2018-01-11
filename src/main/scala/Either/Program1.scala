package Either

object EitherTest1 extends App {
  val in = Console.readLine("Type Either a String or an Int: ")

  val result: Either[String, Int] = try {
    Right(in.toInt)
  } catch {
    case _: Exception => Left(in)
  }

  println(result match {
    case Right(x) => s"You passed me the Int: $x, which I will increment.$x + 1 = ${x + 1}"
    case Left(x)  => s"You passed me the String: $x"
  })
}