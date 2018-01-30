val s1 = "a"
val res1 = if(s1.matches("[a-zA-Z]+")) true else false

//+ means "one or more"
val s2 = ""
val res2 = if(s2.matches("[a-zA-Z]+")) true else false


val s3 = "12"
val res3 = if(s3.matches("[a-zA-Z]+")) true else false

//[a-zA-Z] mean anything from a to z or A to Z
val s4 = "hello"
val res4 = if(s4.matches("[A-Z]+")) true else false

val s5 = "1234567890"
val res5 = if(s5.matches("[0-9]+")) true else false

//. means any character(means it should exist once)
val s6 = "a"
val res6 = if(s6.matches(".")) true else false

val s7 = "" // no characters
val res7 = if(s7.matches(".")) true else false

val s8 = "&"
val res8 = if(s8.matches(".")) true else false

val s9 = "abc" //many characters
val res9 = if(s9.matches(".")) true else false

val s10 = "abc" //any characters, one or more times
val res10 = if(s10.matches(".+")) true else false

val s11 = "" //any characters, one or more times, here zero times
val res11 = if(s11.matches(".+")) true else false

//so,
//* means any number of times

val s12 = "" //any characters, zero or more times, here zero times
val res12 = if(s12.matches(".*")) true else false

val s13 = "abc" //any characters, one or more times
val res13 = if(s13.matches(".*")) true else false

//case I

val str = """ID:   12343-7888d""".stripMargin

val res = if(str.matches("[a-zA-Z]+:.*")) true else false