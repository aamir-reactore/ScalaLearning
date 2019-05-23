package martinoderskybook.ControlStructures
import scala.collection.immutable.Seq

object MultiplicationTable extends App {

  //returns row as a sequence
   def makeRowSeq(row:Int): Seq[String] = for {
     col <- 1 to 10
   } yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
   }
  //returns row as a string
  def makeRow(row:Int) = makeRowSeq(row).mkString

  def multiTable: String = {
    val tableSeq: Seq[String] =
      for(row <- 1 to 10)
        yield makeRow(row)
    tableSeq.mkString("\n")
  }
  println(multiTable)
}