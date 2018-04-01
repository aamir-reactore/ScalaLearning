val l1 = List(1, 2, 3, 4) :: Nil

val _2d1 = List(
  List(1, 2, 3),
  List(4, 5, 6)
)

val _2d2 = (
  1 :: 2 :: 3 :: Nil,
  4 :: 5 :: 6 :: Nil
)

val _2d3 = (1 :: 2 :: 3 :: Nil) ::
  (4 :: 5 :: 6 :: Nil) :: Nil

// :: associates from right
