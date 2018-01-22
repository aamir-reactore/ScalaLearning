val values: List[Int] = List(1, 2, 3, 4, 5)
val result: Seq[List[Int]] = values.grouped(2).toSeq
result.foreach(println)