/**
  * Finding regex pattern in Scala String.

  */
/*val numPattern = "[0-9]+".r
val address = "123 Main Street Suite 101"
val add1 = "main 16th"

val match1 = numPattern.findFirstIn(address)
val match2 = numPattern.findFirstIn(add1)*/

/**
  * Some examples
  */
/*
val fileName1 = "common-api.9.2.2.2"
val temp1 = fileName1.replaceAll("[^0-9.]+", "")

val fileName2 = "common-api.9.2.2.2"
val regex1 = "[0-9.]+".r
val temp2 = regex1.findFirstIn(fileName2)

val s1 = "123ab3c2"
val r1 = if(s1.matches("[0-9].*")) true else false

val s = "123ab3c2"
val r = if(s.matches("[0-9a-zA-Z]")) true else false*/

val ra = new scala.util.Random
val ras = 20.0 + ra.nextInt(( 30 - 20) + 1)