package eciffocodeexamples.csvfilewriter

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

object MakeCSV extends App {

  val file = new File("/home/aamir/file.old.csv")

  if (!file.exists()) {
    file.createNewFile
  }

  val outputFile = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsoluteFile, true)))
  outputFile.write("aamir,fayaz,wani")
  outputFile.write("\n")

  outputFile.flush()
  outputFile.close()
}