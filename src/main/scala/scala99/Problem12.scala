package scala99

//reverse Run-length encoding of a list modified, P11 uncompressed version
object DuplicateListByLengthEncodingTuple extends App {

  val l: Seq[Any] = List((4,'a'), 'b', (2,'c'), (2,'a'), 'd', (4,'e'))

  val res = l flatMap {
      case (n:Int, m) => List.fill(n)(m)
      case x => x :: Nil //List(x)
    }
   println(res)

}