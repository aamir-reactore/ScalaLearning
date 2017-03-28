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