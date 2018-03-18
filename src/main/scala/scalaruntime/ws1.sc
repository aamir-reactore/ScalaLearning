import org.joda.time.LocalDate

import scala.reflect.runtime.universe._
val x = typeOf[Option[_]]
val y = x.decls
y.isInstanceOf[Traversable[_]]

/**
Scala generic types which are present at compile time are erased at
runtime (erasure). TypeTags are b.a way of having access to that
lost compile time information at runtime
  */
def getType[T:TypeTag](obj:T) = typeOf[T]
getType(List(1,2,3))

class Animal
class Cat extends Animal
val a = new Animal
getType(a)
val c = new Cat
getType(c)






