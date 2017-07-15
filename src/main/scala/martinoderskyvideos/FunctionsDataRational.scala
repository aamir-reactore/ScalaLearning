package martinoderskyvideos

class Rational(x:Int,y:Int) {
   require(y != 0, "denominator must not be zero")
  def gcd(a:Int,b:Int):Int = if(b == 0) a else gcd(b, a % b)
  def numer= x / gcd(x, y)
  def denom = y / gcd(x, y)

  def +(that:Rational): Rational = new Rational(numer * that.denom + that.numer * denom,denom * that.denom)
  def unary_- : Rational = new Rational(-this.numer,this.denom)
  def -(that:Rational): Rational = this + -that
  def <(that:Rational):Boolean = numer * that.denom < that.numer * denom
  def max(that:Rational):Rational = if(this.<(that)) that else this
  override def toString: String = s"$numer / $denom"
}
object FunctionsDataRational1 extends App {

   val x = new Rational(1,2)
   val y = new Rational(2,3)
  val z = new Rational(5,7)
  println(x + y)
  println(z + z)
  println(x - y)

  println(x < y)
  println(x max y )

  //val p = new Rational(2,0)
}
//In Scala, symbol can be used as identifiers,