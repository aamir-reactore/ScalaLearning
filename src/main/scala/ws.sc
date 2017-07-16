val str = "Hello"
str.isInstanceOf[String]

class Person

case object P

val obj = new Person
obj.isInstanceOf[Serializable]
P.isInstanceOf[Serializable]

class Reactore(fn: String, mn: String, ln: String) {
  def this(fn: String, ln: String) {
    this(fn, "Ahmad", ln)
  }
}

object Reactore {
  def apply(fn: String, mn: String, ln: String) = {
    new Reactore(fn, mn, ln)
  }

  def apply(fn: String, ln: String) = {
    new Reactore(fn, "Ahmad", ln)
  }
}

val obj1 = new Reactore("arif", "ahmad", "bhat")
val obj2 = new Reactore("arif", "bhat")

val obj3 = Reactore("arif", "ahmad", "bhat")
val obj4 = Reactore("arif", "bhat")






















