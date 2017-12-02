def greet() = println("I'm sorry, I've side effects!")
greet() == ()

//2d collection
for(
  i <- 1 to 2;
  j <- i to 2
) yield i * j

 val str = "line = abc gcd hello how arwe you sir, for grep test string.mathes patternf"
str.matches(".*gcd.*")
