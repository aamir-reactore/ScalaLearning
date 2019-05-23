
//def implicitly[T](implicit e: T): T = e
implicit val a: String = "hello" // define an implicit value of type String
val b: String = implicitly[String] // search for an implicit value of type String and assign it to b
//val c = implicitly[Int], error: could not find implicit value for parameter e: Int
//val c = implicitly[Int]