package generics.contravariance

object Program1 extends App {
  class Animal(name: String)
  class Dog(name: String) extends Animal(name)

  val an: Animal = new Dog("labradog") // normal sub-typing (Polymorphism)

  class GenericType[-T](a: T)

  var c1: GenericType[Animal] = new GenericType[Animal](a1)

  val c2: GenericType[Dog] = new GenericType[Dog](d1)

  val a1 = new Animal("")
  val d1 = new Dog("")

  val a:GenericType[Dog] = new GenericType[Dog](d1)

  val z:GenericType[Animal] = new GenericType[Animal](a1)

  val zz:GenericType[Animal] = z

  val zzz:GenericType[Dog] = zz

  def m1(x:GenericType[Animal]) = {

  }

  m1(c1)
  //m1(c2)

  def m2(x:GenericType[Dog]) = {

  }
  m2(c2)
  m2(c1)
}

object Program2 extends App {

   trait Type[-T] {
     def typeName: Unit
   }

  class SuperType extends Type[AnyVal] {
    def typeName = println("Super Type")
  }

  class SubType extends Type[Int] {
    def typeName = println("Sub type")
  }

  class TypeCarer {
    def displayName(t: Type[Int]) = {
      t.typeName
    }
  }

  val superType = new SuperType
  val subType = new SubType

  val typeCarer = new TypeCarer
  typeCarer.displayName(superType)
  typeCarer.displayName(subType)
}