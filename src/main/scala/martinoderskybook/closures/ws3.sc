//Indirect recursion (mutually recursive functions)
def isEven(x: Int): Boolean =
  if (x == 0) true else isOdd(x - 1)
def isOdd(x: Int): Boolean =
  if (x == 0) false else isEven(x - 1)
isEven(5)
isEven(4)
isOdd(4)
isOdd(5)

/**
   You won’t get a tail-call optimization,
   if the final call goes to a function value
  */
val funValue = nestedFun _
def nestedFun(x: Int) {
  if (x != 0) { println(x); funValue(x - 1) }
}
funValue(2)
nestedFun(2)

