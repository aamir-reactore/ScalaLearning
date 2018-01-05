package Collections


import org.scalatest.{Matchers, WordSpec}

class ListTest1 extends WordSpec with Matchers {

  "List Test" should {
    "test if two unsorted list are equal" in {
      List(1, 2, 3, 4) should contain theSameElementsAs List(2, 3, 1, 4)
    }
  }
}