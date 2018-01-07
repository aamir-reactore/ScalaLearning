package healthyfruits {
  class Apple1 {

  }
  class Mango1 {

  }
  class Pears1 {

  }
  package unHealthyfruits {
    class CustardApple {

    }
  }
}

object ImportTest1 extends App {
  import healthyfruits.{Apple1 => MyApple,Pears1}
  new healthyfruits.Apple1
  new MyApple
  // CTE new Mango
  new Pears1
}

object ImportTest2 extends App {
  import healthyfruits.{unHealthyfruits => mystylefruits}
  import mystylefruits.{CustardApple => SillyApple}
  new SillyApple
  new mystylefruits.CustardApple
  new healthyfruits.unHealthyfruits.CustardApple
}

object ImportTest3 extends App {
  import healthyfruits.{Pears1 =>  _,_}
  new Mango1
  new Apple1
  //new Pears1, CTE
}
object ImportTest4 extends App {
  //if both Notebooks, Fruits package has Apple Object
  //import Notebooks._
  //import Fruits.{Apple => _,_}
  //then only Apple from Notebooks is imported.
}

object ImportTest5 extends App {
  /**
    * Scala implicity imports these three in all Scala files
    */
  import java.lang._
  import scala._
  import Predef._
  //which  member is imported if present in all packages,
  //latest one defined e.g here Predef._ 's member will be imported
}