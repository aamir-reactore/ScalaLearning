package io

import java.io.File

object CountFilesInFolder extends App {


  def countLines(filename: String): Option[Long] = {
    val NEWLINE = 10
    var newlineCount = 0L
    var source: scala.io.BufferedSource = null
    try {
      source = scala.io.Source.fromFile(filename)
      for (line <- source.getLines) {
        newlineCount += 1
      }
      Some(newlineCount)
    } catch {
      case e: Exception => None
    } finally {
      if (source != null) source.close
    }
  }

  def getFileLines(fileName: String) = {
    val result = countLines(fileName)
    if(result.isDefined) result.get else 0L
  }
  def walk(path: String): Unit = {
    val root = new File(path)
    val list = root.listFiles
    for (f <- list) {
      if (f.isDirectory) {
        walk(f.getAbsolutePath)
        System.out.println("Folder:" + f.getAbsoluteFile)
      }
      else {
        println("File-Name: " + f.getAbsoluteFile.getName + ", File-Size:" + f.length / 1024 + " kb" + ", total-records:" + getFileLines(f.getAbsolutePath))
      }
    }
  }

  walk("C:\\Workspace\\ZettaSense_A\\data") //e.g   walk("your root path here")

}