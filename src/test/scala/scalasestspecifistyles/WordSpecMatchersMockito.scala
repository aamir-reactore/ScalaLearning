/*
package scalasestspecifistyles

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import org.mockito.Mockito.{when,verify}
class SimpleTest extends WordSpec with Matchers with MockitoSugar {

  "List Test" should {
    "test if two unsorted list are equal" in {
      List(1, 2, 3, 4) should contain theSameElementsAs List(2, 3, 1, 4)
    }
  }
  "calling head" should {
    "throw an exception for empty head" in {
      intercept[NoSuchElementException] {
        List[Int]().head
      }
    }
  }
  "mocking a list" should {
    "returned trained value for head" in {
      val mockList = mock[List[Int]]
      when(mockList.head).thenReturn(1)
      mockList.head shouldBe 1
      verify(mockList).head //verify if head method was invoked or not
    }
  }
}*/
