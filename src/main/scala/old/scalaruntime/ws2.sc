
//latest typeTag check
import scala.reflect.runtime.universe._
val tt1 = typeTag[Int]

import scala.reflect.runtime.{universe => rt}
val tt2 = rt.typeTag[Float]

import scala.reflect._
val ct1 = classTag[String]

import scala.reflect._
val ct2 = classTag[List[Int]]