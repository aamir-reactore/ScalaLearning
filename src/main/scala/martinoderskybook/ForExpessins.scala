package martinoderskybook

import java.io.File


object forcomp1 extends App {
  for (i <- 1 to 4) println(s"i value is -> $i")
}

object forcomp2 extends App {
  val fileList = new File("/home/aamir/").listFiles()
  for(file <- fileList if file.getName.endsWith(".sbt"))
     println(s"file name is ${file.getName}")
}
object forcomp3 extends App {
  val fileList = new File("/home/aamir/").listFiles()
  for (
    file <- fileList
    if file.isFile
    if file.getName.endsWith(".pdf")
  ) println(file)
}

object forcomp4 extends App {
  def fileLines(file:java.io.File): Iterator[String] = {
    println("came here*****")
    scala.io.Source.fromFile(file).getLines()
  }

  def grep(pattern:String) = {
    for (
      file <- new File("/home/aamir/").listFiles()
      if file.getName.endsWith("list.txt");
      line <- fileLines(file)
      if line.trim.matches(pattern)
    ) println(s"$file : ${line.trim}")
  }
  grep(".*Symphony.*")

}

object forcomp extends App {

   val res = for(
         i <- 1 to 4;
         j <- i to 4
   ) yield i + j

  res foreach(println)
}