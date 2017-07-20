package martinoderskyvideos.week4

//paeno numbers
object Nats extends App {

  abstract class Nat {
    def isZero: Boolean
    def predecessor : Nat
    def successor = new Succ(this)
    def + (that: Nat): Nat
    def - (that: Nat): Nat
  }

  object Zero extends Nat {
    def isZero: Boolean = true
    def predecessor : Nat = throw new Exception("Zero doesn't have a predecessor")
    def +(that: Nat): Nat = that
    def -(that: Nat): Nat = throw new Exception("A nat can't be negative")
    override def toString = "0"
  }

  class Succ(n: Nat) extends Nat {
    def isZero: Boolean = false
    def predecessor : Nat = n
    def + (that: Nat): Nat = if(that.isZero) this else n + that.successor
    def - (that: Nat): Nat = if(that.isZero) this else n - that.predecessor
    override def toString = n.toString + "+1"
  }

  val one = new Succ(Zero)
   println("one is==> " + one)
   println("predecessor of one is ===>" + one.predecessor)
   println("successor is one is === >" + one.successor)
  println("zero plus one => " + (one))
  println("two is ==>" + new Succ(one))
  println("three is ==>" + new Succ(new Succ(one)))
  println("four is ==>" +new Succ(new Succ(new Succ(one))))

  println("four minus two is ==> " + (new Succ(new Succ(new Succ(one))) - new Succ(one)))
//  println( Zero.successor + Zero.successor )

}