/*
package old.datastructures.steps
import cucumber.api.scala.{EN, ScalaDsl}
import old.cucumbertest.MyCalculator

/**
  * Ref:https://alexromanov.github.io/2016/07/15/cucumber-scala-sbt/
  *
  * https://github.com/Originate/guide/blob/master/cucumber.md
  */
 class StepDefinitions extends ScalaDsl with EN {
  var calc: MyCalculator = _
  var result: Int = _

  Given("""^my calculator is running$"""){ () =>
    calc = new MyCalculator
  }
  When("""^I add (\d+) and (\d+)$"""){ (firstNum:Int, secondNum:Int) =>
    result = calc.add(firstNum, secondNum)
  }
  Then("""^result should be equal to (\d+)$"""){ (expectedResult:Int) =>
    assert(result == expectedResult, "Incorrect result of calculator computation")
  }
  When("""^I subtract (\d+) and (\d+)$"""){ (firstNum:Int, secondNum:Int) =>
    result = calc.sub(firstNum, secondNum)
  }

}
*/
