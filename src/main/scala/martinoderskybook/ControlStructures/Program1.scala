package martinoderskybook.ControlStructures

import java.io.{File, FileNotFoundException, IOException}

object ForComprehension1 extends App {
  def filesHere: Array[java.io.File] = new java.io.File("/home/aamir/").listFiles()

  def fileLines(file: java.io.File): Iterator[String] = scala.io.Source.fromFile(file).getLines()

  def grep1(pattern: String) = {
    for (
      file <- filesHere
      if file.getName.endsWith(".sbt")
    ) println(s".sbt file is$file")
  }

  def grep2(pattern: String): Array[File] = {
    for (
      file <- filesHere
      if file.getName.endsWith(".sbt")
    ) yield file
  }

  grep1(".*gcd.*")
  val res: Array[File] = grep2(".*gcd.*")

}

object ForComprehension2 extends App {
  def filesHere: Array[java.io.File] = new java.io.File("/home/aamir/").listFiles()

  def fileLines(file: java.io.File): Iterator[String] = scala.io.Source.fromFile(file).getLines()

  def grep(pattern: String) = {
    for (
      file <- filesHere
      if file.getName.endsWith(".scala")
      if file.isFile;
      line <- fileLines(file);
      trimmed = line.trim
      if trimmed.matches(pattern)
    ) yield trimmed
  }

  val res: Array[String] = grep(".*gcd*.")
  res foreach {
    println
  }
}

object Exception1 extends App {
  val res1: Int = try {
    1
  } catch {
    case e: FileNotFoundException => throw e
    case e: IOException           => throw e
  } finally {
    2
  }

  def g(): Int = {
    try return 1 finally return 2
  }

  println(res1)
  println(g)
}

object PatternMatching1 extends App {
  val res: String = "pofu" match {
    case "gony" => "gony"
    case "pofu" => "pistola"
    case "7"    => "7"
    case _      => "larif"
  }
  println(res)
}

object BreakTest extends App {
  var x = 10 //bad
  MyBreakableBlock {
    while (x > 1) {
      if (x == 5) break
      println(s"x = $x")
      x -= 1
    }
  }

  def MyBreakableBlock(op: => Unit): Unit = {
    try {
      op
    } catch {
      case e: Exception => {
        if (e eq new Exception) // comparing a fresh object using `eq' will always yield false
          println("breaked")
        else
          println("breakebad")
      }
    }
  }

  def break: Nothing = {
    throw new Exception("control structures")
  }

}
