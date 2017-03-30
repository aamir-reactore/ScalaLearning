class Flat(size:String) {
  def xx = size
}
val obj = new Flat("2 BHK")
obj.xx
obj.xx

case class x(var name:String)
val d = x("abc")
d.name
d.name = "Abc"

class St {
  private[this] var num = 10
  def met(obj: St) = num > 10
}

class Person(firstLast:String) {
   private[this] val firstLastArr = firstLast.split(" ")
   val fn = firstLastArr(0)
  val ln = firstLastArr(1)
   }

class R123 private(val x:Int, val y:Int)
val x1 = new R123(1, 2)
x1.x



