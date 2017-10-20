package ftp.writeToFTPFile

import java.io._
import java.net.URL

import org.apache.commons.net.ftp.FTPClient

/**
  * Created by aamir on 20/10/17.
  */
object WritetoFTPFile1 extends App {

  val ftp = new FTPClient()
  ftp.connect("185.7.81.103")
  println("login: "+ftp.login("ftpuser", "password123@"))
  ftp.changeWorkingDirectory("/SharedLocation/SAP_IN/")
  // list the files of the current directory
  val files = ftp.listFiles().toList //will list files and folders.
    println("Listed "+files.size+" files.")
  val list = List("\nw1,NI22k,2014-09-09,miki,12:34",
    "\nw2,NI22k,2014-09-09,miki,12:34",
    "\nWaste,NI22k,2014-09-09,miki,12:34",
    "\nWaste,NI22k,2014-09-09,miki,12:34")
  files.foreach { x =>
     if(x.isFile) {
       val remoteFilename = s"/SharedLocation/SAP_IN/${x.getName}"
       list map {x =>
         val local = new ByteArrayInputStream(x.toCharArray.map(_.toByte))
         ftp.appendFile(remoteFilename, local)
       }
     }
   }
}
