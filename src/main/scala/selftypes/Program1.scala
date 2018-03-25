package selftypes

/**
  A self-type for a trait A:

trait B
trait A { this: B => }

says that "A cannot be mixed into a concrete class that does not also extend B".

  or for A to be mixed into a concrete class that class must extends B

On the other hand, the following:

trait B
trait A extends B

says that "any (concrete or abstract) class mixing in A will also be mixing in B".

  ==>Now, as to what is the difference between a self type and extending a trait, that is simple.
  If you say B extends A, then B is an A. When you use self-types, B requires an A.
  */
trait User {
  def name: String
}

trait Tweeter  {
  user: User =>
  def tweet(msg: String) = println(s"$name: $msg")
}

/*trait Wrong extends Tweeter {
  def noCanDo = name
}*/

trait DummyUser extends User {
  override def name: String = "foo"
}

trait RightAgain extends Tweeter with DummyUser {
  val canDo: String = name
}

trait Right extends Tweeter with User {
  val canDo = name
}

trait LL extends RightAgain
trait MM extends Right {

}


