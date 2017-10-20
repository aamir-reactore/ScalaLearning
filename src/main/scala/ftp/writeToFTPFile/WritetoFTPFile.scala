package ftp.writeToFTPFile

import java.io._
import java.net.URL

import org.apache.commons.net.ftp.FTPClient

/**
  * Created by aamir on 20/10/17.
  */
object WritetoFTPFile1 extends App {
  val psscode = "password123@"
  val userName = "ftpuser"
  val ip = "185.7.81.103"
  val filePath = "/SharedLocation/SAP_IN/Serv_20171004.csv"

  val serv = s"ftp://$userName:$psscode @$ip$filePath;type=i"

  val  url = new URL(serv)
  val urlc = url.openConnection()
  val os = urlc.getOutputStream()
  val buffer = new BufferedOutputStream(os)
  val output = new ObjectOutputStream(buffer)
  val myObject = "somedata,data,data"
  output.writeObject(myObject)
  buffer.close()
  os.close()
  output.close()
}
object WritetoFTPFile2 extends App {

  val ftp = new FTPClient()
  ftp.connect("185.7.81.103")
  println("login: "+ftp.login("ftpuser", "password123@"))
  ftp.changeWorkingDirectory("/SharedLocation/SAP_IN/")
  // list the files of the current directory
  val files = ftp.listFiles().toList //will list files and folders.
    println("Listed "+files.size+" files.")
   files.foreach { x =>
     if(x.isFile) {
       val remoteFilename = s"/SharedLocation/SAP_IN/${x.getName}"
       val text = "\nWaste,NI22k,2014-09-09,miki,12:34"
       val local = new ByteArrayInputStream(text.toCharArray.map(_.toByte))
       ftp.appendFile(remoteFilename, local)
     }
   }

}
