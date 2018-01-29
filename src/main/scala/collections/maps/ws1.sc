/**
  * map() method requires case pattern matching
  *  but transform() doesn't
  */
val original = Map("A"-> 1,"B"-> 2)
original.map { case (k, v) => (k, v + 1) }
original.transform((_, v) => v + 1)
