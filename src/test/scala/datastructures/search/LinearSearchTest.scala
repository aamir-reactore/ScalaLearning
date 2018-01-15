package datastructures.search

import org.scalatest.{Matchers, WordSpec}

class LinearSearch1Test extends WordSpec with Matchers {
   import datastructures.search.LinearSearch1._
  "Linear Search" should {

    "return None when list is empty" in {
      val list = Nil
      val key = 12
      val result = searchLinear(list,key)
      val expectedResult = None
      result shouldBe expectedResult
    }
    "returns found element in the list" in {
      val list = List(1,2,3,4,5,6)
      val key = 4
      val result = searchLinear(list,key)
      val expectedResult = Some(4)
      result shouldBe expectedResult
    }
    "return None if key is not in the list" in {
      val list = List(1,2,3,4,5,6)
      val key = 40
      val result = searchLinear(list,key)
      val expectedResult = None
      result shouldBe expectedResult
    }
  }
}