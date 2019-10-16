/*
/**
Self types allow you to define cyclical dependencies. For example, you can achieve this:
  **/
/**
trait A extends B
trait B extends A

  CTE: Error:(5, 9) illegal cyclic reference involving trait A
trait B extends A

  **/
trait A { self: B => }
trait B { self: A => }
//trait B extends A{ }
/**
  * self-types can specify non-class types
**/
trait Foo{
  this: { def close:Unit } =>
}

/**
  * The self type here is a structural type. The effect
  * is to say that anything that mixes in
  * Foo must implement a no-arg "close" method returning unit
  */
trait Woo extends Foo {
  def close = println("")
}*/
