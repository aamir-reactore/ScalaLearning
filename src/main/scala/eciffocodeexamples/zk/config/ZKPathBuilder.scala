package eciffocodeexamples.zk.config

import java.lang.reflect.Field

import com.typesafe.config.ConfigFactory
import eciffocodeexamples.zk.config.ZkPathBuilder.Root.Environment.Service.Module

trait Copyable {
  def copyWith(changes: (String, AnyRef)*): Copyable = {
    val clas = getClass
    val constructor = clas.getDeclaredConstructors.head
    val argumentCount = constructor.getParameterTypes.length
    val fields = clas.getDeclaredFields
    verify(fields, changes: _*)
    // if(fields.find(f => f.getName == changes))
    val arguments = (0 until argumentCount) map { i =>
      val fieldName = fields(i).getName
      changes.find(_._1 == fieldName) match {
        case Some(change) => {
          if (change._2.isInstanceOf[Some[_]])
            throw new IllegalArgumentException(s"Do not wrap the value in Some(${change._2}) while using copyWith of Copyable, exception occured at ${clas.getName}")
          if (fields(i).getType == classOf[Option[_]] && change._2 != None)
            Some(change._2)
          else change._2
        }
        case None         => {
          val getter = clas.getMethod(fieldName)
          getter.invoke(this)
        }
      }
    }
    constructor.newInstance(arguments: _*).asInstanceOf[Copyable]
  }

  private def verify(fields: Array[Field], changes: (String, AnyRef)*) = {
    changes.foreach(change => {
      if (!fields.exists(f => f.getName == change._1)) {
        println(
          s"""
             |=================================================================================
             |
             | Field name (${change._1}) not found in entity (${this.getClass.getName}) while copying new value (${change._2})
             |
             |=================================================================================
              """.stripMargin)
        throw new IllegalArgumentException(s"Field name (${change._1}) not found in entity (${this.getClass.getName}) while copying new value (${change._2}) ")
      }
    })
  }
}

class ZkNode(private[config] val path: String) extends Copyable {

  def withNode(extendedPath: String): ZkNode = new ZkNode(this.path+ "." + extendedPath) //this.copyWith(("path", path + "." + extendedPath)).asInstanceOf[Path]

  def withProperty(propertyName: String): String = path + "." + propertyName
}

object ZkPathBuilder {

  //  val Service: String = s"system.$environment.service"

  val config = ConfigFactory.load()
  lazy val environment = config.getString("environment")
  lazy val moduleName = config.getString("moduleCode")

  object Root extends ZkNode("system") {

    object Environment extends ZkNode(Root.path + "." + environment) {

      object Service extends ZkNode(Environment.path + ".service") {

        object Global extends ZkNode(Service.path + ".global")

        object Module extends ZkNode(Service.path + ".module." + moduleName.toLowerCase)

        object Integrations extends ZkNode(Service.path + ".integrations")

      }

    }

  }

}

object zkTest extends App with ZooKeeperConfiguration {
  def getSettingAsString(path: String, defaultValue: String) = {
    ZKConfigHelperObj.getString(path, defaultValue).asString
  }
  val node =  Module.withNode("mynode").withNode("node1").withProperty("prop1")
  val prop = getSettingAsString(node,"propValue")
  println(prop) // displays props value
}