package collections.lists

object YourOwnForEach1 extends App {


    val f = (elem: Int) => println(elem)

    val l = List(1,2,3,4,5)

    l.foreach(println)
  }


  class MyList[+A] {



    def foreach[U](f: A => U) = {

    }

    def x:Nothing = {
      throw new Exception("")
    }

}