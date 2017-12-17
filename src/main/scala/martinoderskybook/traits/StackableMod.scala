abstract class Bar { def bar(x: Int): Int }
class Foo extends Bar { def bar(x: Int) = x }

then for Foo "super" will always be Bar.

  If you write

trait Foo1 extends Foo { abstract override def bar(x: Int) = x + super.bar(x) }

Then for that method super remains unknown until the class is made.

trait Foo2 extends Foo { abstract override def bar(x: Int) = x * super.bar(x) }

scala> (new Foo with Foo2 with Foo1).bar(5)
res0: Int = 30

scala> (new Foo with Foo1 with Foo2).bar(5)
res1: Int = 50