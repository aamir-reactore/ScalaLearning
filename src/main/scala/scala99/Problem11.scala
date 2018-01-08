package scala99
// Run-length encoding of a list modified
object RunLengthEncodingListPatterMatch extends App {

  val l = List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e'))

  //pattern matching on tuples
  val res = l map {
    case (1, x) => x
    case y => y
  }

  println(res)

}