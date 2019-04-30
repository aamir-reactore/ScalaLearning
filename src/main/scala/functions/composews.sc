object testcomposite extends App {
  def x1(x: Int) = x + x

  def x2(x: Int) = x * x

  /*def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"
val fComposeG = f _  compose g
fComposeG("hi")*/

  val r = x1 _ compose x2
  r(2)
}

