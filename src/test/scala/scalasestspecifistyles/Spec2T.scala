
package scalasestspecifistyles

import org.mockito.Mockito.when
import org.specs2.mock.Mockito
import org.specs2.mutable._

abstract class Foo {
  def getSomething(): String
}

/*class WithMockitoSuite extends FunSuite with ShouldMatchers {
  test("mockito") {
    val foo = mock(classOf[Foo])
    when(foo.getSomething()).thenReturn("sss")
    foo.getSomething() should be("sss")
  }
}*/
class Spec2SpecTest extends Specification with Mockito {

  "calling head" should {
    "throw an exception for empty head" in {
      //List[Int]().head must throwA[NoSuchElementException]
      List().isEmpty must beTrue
    }
  }
  "getSomething method" should {
    "return text as ss when called" in {
      val foo = mock[Foo]
      when(foo.getSomething()).thenReturn("sss")
      foo.getSomething() mustEqual "sss"
    }
  }
}
