package json

import org.json4s.Extraction
import org.json4s.JsonAST.JValue
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods.{compact, parse, render}

import scala.util.Try
trait RequestResponseEntity
abstract class JsUBEntity[E, TId](implicit val manifest1: Manifest[E]) {
  def attributes: Map[String, String] = Map.empty

  val createdBy: Long
}
trait Persisteble[TId] extends RequestResponseEntity {
  val id: TId
}
abstract class AggregateRoot[E, TId](implicit val mf: Manifest[E]) extends JsUBEntity[E, TId] with Persisteble[TId] {
  /*  def set(cc:Long ,createdDate:Timestamp,modifiedBy:Long,modifiedDate:Timestamp)(implicit session:Session)={
       createdBy = cc
    }*/
}
trait BaseJsonUtilitiesComponent  { this : BaseTypeSerializers =>
  import scala.language.implicitConversions

  implicit class CustomJson(jValue: JValue) {
    def append(key: String, c: Any): JValue = jValue.merge(render(key -> Extraction.decompose(c)))

    def appendJson(key: String, value: String) = jValue.merge(render(key -> parse(value)))

    def append(tuple: (String, Any)): JValue = jValue.merge(render(tuple._1 -> Extraction.decompose(tuple._2)))

    def append(key: String, c: Option[Any]): JValue = jValue.merge(render(key -> Extraction.decompose(c)))

    def append(key: String, c: List[Any]): JValue = jValue.merge(render(key -> Extraction.decompose(c)))

    def append(c: JValue): JValue = jValue.merge(c)

    def append(c: AnyRef): JValue = jValue.merge(Extraction.decompose(c))

    def appendList(key: String, list: List[JValue]) = jValue.merge(render(key -> Extraction.decompose(list)))

    def remove(key: String): JValue = jValue.removeField { x => x._1 == key}

    def remove(keys: List[String]): JValue = jValue.removeField { x => keys.contains(x._1)}

    def asJson = compact(jValue)
  }



  implicit def convertToJValue(x: Any) = new CustomJson(Extraction.decompose(x))

  implicit def convertToJValue2(x: Option[Any]) = new CustomJson(Extraction.decompose(x))

  // x.getOrElse(null)//new CustomJson(Extraction.decompose(x))
  implicit def convertToJValue3(x: (String, Option[Any])) = new CustomJson(render(x._1 -> Extraction.decompose(x._2.getOrElse(null))))

  implicit def convertToJValue4(x: (String, List[JValue])) = new CustomJson(render(x._1 -> x._2))

  implicit def convertToJValue1(d: (String, AggregateRoot[_,Long])) = new CustomJson(render(d._1 -> Extraction.decompose(d._2)))

  implicit def convertJValueToString(jValue: JValue): String = compact(jValue)

  implicit def convertJValueToString(jValueList: List[JValue]): String = compact(jValueList)

  def toJson(value: Any): String = {
    if (value.isInstanceOf[String]) value.asInstanceOf[String] else value.asJson
  }


  @deprecated("Use extractEntityWithTry instead", "Jul 13, 0.8.9.71-DEV-SNAPSHOT")
  def extractEntity[E](json: String)(implicit m: Manifest[E]): E = parse(json).extract[E]

  def extractEntityOpt[E](json: String)(implicit m: Manifest[E]) = parse(json).extractOpt[E]

  def extractEntityWithTry[E](json: String)(implicit m: Manifest[E]): Try[E] = Try(parse(json).extract[E] )

  def toJsonWithPreserve(value: JValue): String = {
    compact(render(value))
  }
}
object BaseJsonUtilities extends BaseJsonUtilitiesComponent with BaseTypeSerializers

object ll extends App {

  import BaseJsonUtilities._
  case class A(name:String,age:Int)
  val ll  = List(A("aamir",24),A("boy",22))
  println(ll.asJson)

}
