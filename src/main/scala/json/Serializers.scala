package json

import java.sql.Time
import java.text.SimpleDateFormat
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import org.joda.time.{DateTime, LocalTime}
import org.joda.time.format.DateTimeFormat
import org.json4s.JsonAST.JNothing
import org.json4s.{DefaultFormats, FieldSerializer, Formats}

import scala.collection.mutable.ListBuffer
trait NameLike {
  val name: String
}
trait Serializer {

  import org.json4s.CustomSerializer

  val serializers: ListBuffer[CustomSerializer[_]] = ListBuffer()
  val appendedFieldSerializers: ListBuffer[FieldSerializer[_]] = ListBuffer()

  def appendCustomSerializers(newSerializers: List[CustomSerializer[_]]) = {
    //serializers.appendAll(newSerializers)
    newSerializers.foreach(x => serializers.append(x))
  }

  def appendCustomFieldSerializer(newFieldSerializers: List[FieldSerializer[_]]) = {
    //serializers.appendAll(newSerializers)
    newFieldSerializers.foreach(x => appendedFieldSerializers.append(x))
  }

  implicit lazy val serializerFormats: Formats = {
    val formats: Formats = new DefaultFormats {
      override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

      override val fieldSerializers: List[(Class[_], FieldSerializer[_])] = appendedFieldSerializers.map(s => s.mf.runtimeClass -> s).toList

      // override val emptyValueStrategy: EmptyValueStrategy = EmptyValueStrategy.preserve

    } ++ serializers.toList
    formats.preservingEmptyValues
  }//.preservingEmptyValues
}
trait BaseTypeSerializers extends Serializer {

  import org.json4s.CustomSerializer
  import org.json4s.JsonAST.{JNull, JString}

  val timeOnlyFormatter = DateTimeFormat.forPattern("HH:mm:ss")
  val dateOnlyFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
  val dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC()

  case object SqlDateSerializer extends CustomSerializer[java.sql.Date](format => ( {
    case JString(date) => new java.sql.Date(dateOnlyFormatter.parseDateTime(date).getMillis)
    case JNull         => null
  }, {
    case date: java.sql.Date => JString(date.toString)
  }))

  case object SqlTimeSerializer extends CustomSerializer[java.sql.Time](format => ( {
    case JString(time) => new Time(timeOnlyFormatter.parseDateTime(time).getMillis)
    case JNull         => null
  }, {
    case time: java.sql.Time => JString(time.toString)
  }))


  case object LocalDateSerializer extends CustomSerializer[LocalDate](format => ( {
    case JString(date) => LocalDate.parse(date)
    case JNull         => null
  }, {
    case date: LocalDate => JString(date.toString)
  }
  ))

  case object JodaLocalDateSerializer extends CustomSerializer[org.joda.time.LocalDate](format => ( {
    case JString(date) => org.joda.time.LocalDate.parse(date)
    case JNull         => null
  }, {
    case date: org.joda.time.LocalDate => JString(date.toString)
  }
  ))

  case object JodaDateSerializer extends CustomSerializer[DateTime](format => ( {
    case JString(date) => DateTime.parse(date, dateTimeFormat)
    case JNull         => null
  }, {
    case date: DateTime => JString(date.toString(dateTimeFormat))
  }
  ))

  case object JodaTimeSerializer extends CustomSerializer[LocalTime](format => ( {
    case JString(time) => LocalTime.parse(time, timeOnlyFormatter)
    case JNull         => null
  }, {
    case time: LocalTime => JString(time.toString(timeOnlyFormatter))
  }
  ))

  case object UUIDSerializer extends CustomSerializer[UUID](format => ( {
    case JString(id) => UUID.fromString(id)
    case JNull       => null
  }, {
    case uuid: UUID => JString(uuid.toString)
  }
  ))

  //  case object NoneJNullSerializer extends CustomSerializer[Option[_]](format => ( {
  //    case JNull => None
  //    case JInt(id) => Some(id)
  //    case JString(str) => Some(str)
  //    case JDouble(d) => Some(d)
  //   // case JObject(v) => Some(JObjectParser)
  //    //case v:JValue => println("any => "+v); EmptyValueStrategy.preserve.replaceEmpty(v) //Some(any)
  //
  //  }, {
  //    case None => JNull
  //  }
  //  ))

  case object ManifestSerializer extends  CustomSerializer[Manifest[_]](formats => (
    PartialFunction.empty,
    { case _: Manifest[_] => JNothing }
  ))

  val nameListSerializer: FieldSerializer[NameLike] = FieldSerializer[NameLike]()

  appendCustomSerializers(List(SqlDateSerializer, SqlTimeSerializer, LocalDateSerializer, UUIDSerializer, JodaDateSerializer, JodaTimeSerializer, JodaLocalDateSerializer,ManifestSerializer))
  appendCustomFieldSerializer(List(nameListSerializer))
}
object LocalDateExtension {

  implicit class extension(s: LocalDateTime) {
    def between(start: LocalDateTime, end: LocalDateTime): Boolean = {
      s.isAfter(start) && s.isBefore(end)
    }
  }

}

