
object ExecutionTime extends App {

  val startTimeMillis = System.currentTimeMillis()

  println("===sleeping for 10 seconds===")
  Thread.sleep(10000)

  val endTimeMillis = System.currentTimeMillis()

  val durationSeconds = (endTimeMillis - startTimeMillis) / 1000

  println(s"Execution duration: $durationSeconds")
}