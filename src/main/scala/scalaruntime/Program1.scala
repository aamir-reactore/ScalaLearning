package scalaruntime

import scala.reflect.ClassTag

/**
  * Manifests give access to the Java reflection API (eg. method getMethods)
  * without the need for instantiating a value of the instrumented type:
  */
object Program1Test1 extends App {

  def printMethods[T](t: T) { // requires instance
    val meths = t.getClass.getMethods
    println(meths take 3 mkString "\n")
  }

  def printMethods1(name: String) { // low-level
    val meths = Class.forName(name).getMethods
    println(meths take 3 mkString "\n")
  }

  def printMethods2[T: Manifest] { // no instance
    val meths = manifest[T].runtimeClass.getMethods
    println(meths take 3 mkString "\n")
  }

  printMethods(Some(""))
  println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
  printMethods1("scala.Some")
  println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
  printMethods2[Some[_]]
}

/**
  * Subtyping relations can be tested using Scala manifests:
  */
object Program1Test2 extends App {

  def printSubtype[T, U](t: T, u: U) {
    val mt = Manifest.classType(t.getClass)
    val mu = Manifest.classType(u.getClass)
    println(mt <:< mu)
  }

  def printSubtype2[T: Manifest, U: Manifest] {
    println(manifest[T] <:< manifest[U])
  }

  // arrays are invariant
  printSubtype(Array(0), Array[AnyVal](1)) // false
  printSubtype(List(""), List[AnyRef]("")) // true
  printSubtype((Seq(0), 1), (Seq[AnyVal](), 2)) // true

  printSubtype2[Array[Int], Array[AnyVal]]//false
  printSubtype2[List[String], List[AnyRef]]//true
  printSubtype2[(Seq[Int], Int), (Seq[AnyVal], Int)]//true
}

/**
  * Deprecated method <:<, now check implementation:
  * this program to check if array is of a particular type
  */
object Program1Test3 extends App {

  import scala.reflect.runtime.{universe => ru}

  def arrayConformsTo[A: ru.TypeTag](as: Array[_]) = {
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSym = mirror.classSymbol(as.getClass.getComponentType)
    classSym.toType <:< implicitly[ru.TypeTag[A]].tpe
  }

  println(arrayConformsTo[Float](Array[Float]()))
  println(arrayConformsTo[Int](Array[Float]()))
  println(arrayConformsTo[AnyVal](Array[Float]()))
  println(arrayConformsTo[AnyVal](Array[Float]()))
  println(arrayConformsTo[Any](Array[Float]()))
  println(arrayConformsTo[Any](Array[Float]()))
  println(arrayConformsTo[AnyRef](Array[Float]()))
  println(arrayConformsTo[AnyRef](Array[Float]()))

  def arrayConformsTo2[A](as: Array[_])(implicit arrayOfA: ClassTag[Array[A]]) = as match {
    case arrayOfA(_) => true
    case _           => false
  }
  println(":>>>>>>>>>>>>>>>>>>>>>>>>>>>")

  println(arrayConformsTo2[Int](Array[Float]()))
}
