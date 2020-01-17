package martinoderskybook.basictypesandoperations

/**
  * Created by aamir on 25/11/17.
  * Short Circut operator test
  */
object Program1ShortCircuit1 extends App {

   def salt = {println("salt"); false}
   def pepper = {println("pepper"); true}

   println(pepper && salt)
  println("###")
   println(salt   && pepper)
  println("###")
   println(pepper || salt)
 println("---------------------")

  /**
   * If we want to evaluate right hand side No matter what use only 1 & or |
    */
  def salt1 = {println("salt"); false}
  def pepper1 = {println("pepper"); false}

  println(pepper1 & salt1)
  println("###")
  println(pepper1 | salt1)
}

