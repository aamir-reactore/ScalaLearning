package sclala99

//reverse Run-length encoding of a list modified, P11 uncompressed version
object P12 extends App {

  val l: Seq[Any] = List((4,'a'), 'b', (2,'c'), (2,'a'), 'd', (4,'e'))

  val res = l flatMap {
      case (n:Int, m) => List.fill(n)(m)
      case x => x :: Nil
    }
   println(res)

}