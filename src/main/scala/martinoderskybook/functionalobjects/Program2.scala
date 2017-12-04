package martinoderskybook.functionalobjects

class Rational(n:Int, d:Int) /*class parameters*/{
  require(d != 0, "denomenator can't be zero") //java.lang.IllegalArgumentException
  private lazy val g = gcd(n.abs,d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  //  def this(n:Int,m:Int,c:Int) = this(n) CTE must be after def this(n:Int) = this(n,1

  def this(n:Int) = this(n, 1)

  //def this(n:Int,m:Int,c:Int) = this(n) // compiles fine
  override def toString: String = s"{$numer / $denom}"
  def +(that:Rational) = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }
  def +(i:Int) = {
    new Rational(denom * i + numer,denom)
  }

  def *(that:Rational):Rational = {
    new Rational(numer * that.numer,denom * that.denom)
  }
  def lessThan(that:Rational): Boolean = {
    numer * that.denom < that.numer * denom
  }
  def max(that:Rational): Rational = {
    if(lessThan(that)) that else this
  }
  private def gcd(a:Int,b:Int):Int = if(a == 0) b else gcd(b % a,a)
}

object RationalTest extends App {

  val r1 = new Rational(4,2)
  val r2 = new Rational(4,5)
  val addR = r1 + r2
  val mulR = r1 * r2
  println(s"sum ==> $addR")
  println(s"multi ==> $mulR")

  println("##########")

  val singleR = new Rational(4)
  println(s"single R ==> $singleR")
  println(s"multiple operations ${r1 + r2 * r1}") // evaluates as r1 + (r2 * r1)

  println(s"overloaded addition ==> ${r1 + 4}")
  implicit def intToRational(i:Int) = new Rational(i)

  println(s"after implicitly converting==>${2 + r1}")

}