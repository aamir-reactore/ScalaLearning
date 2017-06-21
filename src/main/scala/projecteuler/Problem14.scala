package projecteuler

object CollatzSequenceSol extends App {

  def collatzSeq(num: Long) = {
    def collatzTail(num: Long, acc: Long):Long = {
      if (num == 1) acc + 1 else {
        if (num % 2 == 0)
          collatzTail(num / 2, acc + 1)
        else
          collatzTail((3 * num) + 1, acc + 1)
      }
    }
    collatzTail(num, 0)
  }

  val res = (1 to 1000000).par.maxBy(collatzSeq(_))
  println(res)
}