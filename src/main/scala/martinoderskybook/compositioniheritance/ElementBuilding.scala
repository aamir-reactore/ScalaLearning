package martinoderskybook.compositioniheritance
import Element.elem

abstract class Element {

  def contents:Array[String]
  def height:Int = contents.length
  def width:Int = if(height == 0) 0 else contents(0).length

  def above(that:Element):Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }
  def beside(that:Element):Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(for {
      (line1,line2) <- this1.contents.zip(that1.contents)
    } yield line1 + line2
    )
  }

  override def toString:String = contents mkString "\n"
  def widen(w:Int):Element = {
    if(w <= width) this else {
      val left = elem(' ',(w - width) / 2, height)
      val right = elem(' ', w - width - left.width,height)
     left beside this beside right
    }
  }
  def heighten(h:Int):Element = {
    if(h <= height) this else {
      val top = elem(' ',width,(h - height) / 2)
      val bot = elem(' ',width,h - height - top.height)
      top above this above bot
    }
  }
}

object Element {
  private class ArrayElement(val contents:Array[String]) extends Element
  private class LineElement(s:String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height= 1
  }
  private class UniformElement(ch:Char,override val width:Int,override val height:Int) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  def elem(contents:Array[String]):Element = {
    new ArrayElement(contents)
  }
  def elem(line:String):Element = {
    new LineElement(line)
  }
  def elem(chr:Char,width:Int,height:Int):Element = {
    new UniformElement(chr,width,height)
  }
}

object besidetest extends App {
  val x1 =  Element.elem(Array("kimberly","musadhiq"))
  val x2 =  Element.elem(Array("obaid","fayaz","hello","world","aamir"))
  println(x1 beside  x2)
  println("##########################")
  val x3 =  Element.elem(Array("kimberly","musadhiq","obaid"))
  val x4 =  Element.elem(Array("obaid"))
  println(x3 beside  x4)
  println("##########################")

  val x5 =  Element.elem(Array(""))
  val x6 =  Element.elem(Array("obaid","fayaz","hello","world","aamir"))
  println(x5 beside  x6)
  println("##########################")

  val x7 =  Element.elem(Array("kimberly","musadhiq"))
  val x8 =  Element.elem(Array("obaid","fayaz"))
  println(x7 beside  x8)
}

object abovetest extends App {
  val x1 =  Element.elem(Array("hello","jim","kimberly"))
  val x2 =  Element.elem(Array("sam","goes"))
  println(x1 above  x2)

  println("##########################")

  //depends upon width only
  val x3 =  Element.elem(Array("hello","jim","kimberly","basinger"))
  val x4 =  Element.elem(Array("hiles"))
  println(x3 above  x4)

  println("##########################")

  val x5 =  Element.elem(Array("hi","hello"))
  val x6 =  Element.elem(Array("complete"))
  val h = x5 above  x6
  println(x5 above  x6)
  println(h.width)
}

//testing if override keyword is needed or not

abstract class TestOverride1 {
  def f: String
}

class SubTestOverride1 extends TestOverride1 {
  def f:String = "hello"  // no override keyword needed, here concrete one implements abstract one
}

abstract class TestOverride2 {
  def f: String = "non abstract member"
}

class SubTestOverride2 extends TestOverride2 {
  override def f:String = "hello"  // override keyword needed, here subclass overrides superclass one
}


