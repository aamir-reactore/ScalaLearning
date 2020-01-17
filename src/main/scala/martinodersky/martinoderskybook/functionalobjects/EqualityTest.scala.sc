/**
  * ==, != for value equality and ne,eq for reference equality test
  * */

case class Foo(x: Int)
Foo(2) eq Foo(2)
Foo(2) == Foo(2)

Foo(2) ne Foo(2)
Foo(2) != Foo(2)