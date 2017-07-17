package martinoderskyvideos
//https://stackoverflow.com/questions/16217304/recursive-set-union-how-does-it-work-really
object BinaryInsetTree1 extends App {
 /* val t1= new NonEmptySet(2,new EmptySet, new EmptySet)
   val t2 = t1 incl 1
   val t3 = t2 incl 3
  val o1 = new NonEmptySet(4,new EmptySet, new EmptySet)
  val o2 = o1 incl 5
  val o3 = o2 incl 8
  val res = t3.union(o3)*/

  val t1= new NonEmptySet(4,new EmptySet, new EmptySet)
  val t2 = t1 incl 5
  val t3 = t2 incl 8
  println(t3)
  val o2 = t3 incl 1
  //val o3 = o2 incl 3
  //val o4 = o3 incl 2
 println(o2)
}
abstract class InSet {
def useOverride = 1
def contains(x:Int):Boolean
def incl(x:Int):InSet
def union(other:InSet): InSet
}

class EmptySet extends InSet {

 def contains(x: Int): Boolean = false
 def incl(x: Int): InSet = new NonEmptySet(x,new EmptySet,new EmptySet)
override  def useOverride = 1
override def toString: String = "."
def union(other:InSet): InSet = other
}
class NonEmptySet(elem:Int, left:InSet, right:InSet) extends InSet {
 def contains(x: Int): Boolean = {
  if(x < elem) left contains x
  else if(x > elem) right contains x
  else true
}

 def incl(x: Int): InSet = {
  if(x < elem) new NonEmptySet(elem,left incl x, right)
  else if(x > elem) new NonEmptySet(elem,left, right incl x)
  else this
}

def union(other:InSet): InSet = {
  println(other)
  ((left union right) union other) incl elem
}

override def toString: String = s"{$left$elem$right}"
}
