package scalasestspecifistyles

import org.scalacheck.Properties
import org.scalacheck.Prop.{BooleanOperators, forAll}
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

object ListScalaCheckSpec extends Properties("list") {

  property("concat") = forAll { (a: List[Int], b: List[Int]) =>
    a.size + b.size == (a ::: b).size
  }
  property("head") = forAll { (a: Int) =>
    (a >= 0 && a < 1000) ==> (List(a).head == a)
  }
}
/**
  ScalaCheck:
   -> Automated property based testing.
   -> Uses random generators to generate inputs for multiple property checks
  **/


//also integrates with scala test
class ListCheckerSpec extends FunSuite with Checkers {
  test("concat of 2 list should have size equal to sum of sizes") {
    check((a:List[Int],b:List[Int]) => a.size + b.size == (a ::: b).size)
  }
}