
  def foo[T](t: T)(implicit integral: Integral[T]) {
     println(integral)
  }

  foo(0)
  foo(0L)