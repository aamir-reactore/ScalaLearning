/*

package scopes

object ObjectPrivate1 extends App {

  /**
    * When you do [this],
    * the member is available only to the current instance of the current object.
    * Other instances of the same class cannot access the method.
    */
  class Foo {
    private[this] def isFoo = true
    private[this] val checkFoo = ""
    def doFoo(other: Foo) = {
      if (other.isFoo) {  // this line won't compile
        // ...
      }
    }

    def checkFooM(other: Foo) = {
      val x = other.checkFoo
    }
  }

}
*/
