package slickakkahttp.Entities

import slick.lifted.{Rep, TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape.proveShapeOf
import slickakkahttp.Utilities.{BaseEntity, BaseTable}

/**
  * Created by aamir on 9/3/17.
  */
class EmployeeTable(_tableTag: Tag) extends BaseTable[Employee](_tableTag, None /*use Some(schema name)*/, "Employee") {

  def * = (id, firstName, isDeleted) <> (Employee.tupled, Employee.unapply)

  def ? = (Rep.Some(id), Rep.Some(firstName),Rep.Some(isDeleted)).shaped.<>({ r =>  import r._; _1.map(_ => Employee.tupled((_1.get, _2.get, _3.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

  override val id: Rep[Long] = column[Long]("EmployeeId", O.AutoInc, O.PrimaryKey)
  val firstName: Rep[String] = column[String]("FirstName")
  override val isDeleted: Rep[Boolean] = column[Boolean]("IsDeleted")
  lazy val employeeTable = new TableQuery(tag => new EmployeeTable(tag))
}
case class Employee(id: Long, firstName: String, isDeleted: Boolean) extends BaseEntity

/*
object LTables extends {
  val profile = com.cpny.core.CustomPostgresDriver
} with LTables

trait LTables {
  val profile: CustomPostgresDriver
  class SomeTable(_tableTag: Tag) extends BaseTable[SomeEntity](_tableTag, Some("schema"), "SomeEntity") with ExtensibleTable[SomeEntity] {
    def * = (id, serverConnectionsId, userName, moduleName, currentVersion, lastDownloaded, isInstalled, databaseScriptRunningStatus,
      zookeeperScriptRunningStatus, zookeeper, kafka, zookeeperEnvironment, isRemoved, createdBy, createDate, modifiedBy, modifiedDate, isPendingForApproval, attributes) <> (UserDownloadedModules.tupled, UserDownloadedModules.unapply)

    def ? = (Rep.Some(id), Rep.Some(serverConnectionsId), Rep.Some(userName), Rep.Some(moduleName), Rep.Some(currentVersion), Rep.Some(lastDownloaded), Rep.Some(isInstalled),
      Rep.Some(databaseScriptRunningStatus), Rep.Some(zookeeperScriptRunningStatus), Rep.Some(zookeeper), kafka, Rep.Some(zookeeperEnvironment), Rep.Some(isRemoved), Rep.Some(createdBy),
      Rep.Some(createDate), Rep.Some(modifiedBy), Rep.Some(modifiedDate), Rep.Some(isPendingForApproval),
      Rep.Some(attributes)).shaped.<>({ r =>
      import r._;
      _1.map(_ => UserDownloadedModules.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12.get,
        _13.get, _14.get, _15.get, _16.get, _17.get, _18.get, _19.get)))
    }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    override val id: Rep[Long] = column[Long]("SomeTableId", O.AutoInc, O.PrimaryKey)
    val userName: Rep[String] = column[String]("UserName", O.Length(250, varying = true))
    val moduleName: Rep[String] = column[String]("ModuleName")
    val currentVersion: Rep[String] = column[String]("CurrentVersion")
    val lastDownloaded: Rep[DateTime] = column[DateTime]("LastDownloaded")
    val isInstalled: Rep[Boolean] = column[Boolean]("IsInstalled", O.Default(false))
    val databaseScriptRunningStatus: Rep[Boolean] = column[Boolean]("DatabaseScriptRunningStatus", O.Default(false))
    val zookeeperScriptRunningStatus: Rep[Boolean] = column[Boolean]("ZookeeperScriptRunningStatus", O.Default(false))
    val zookeeper: Rep[String] = column[String]("Zookeeper")
    val kafka: Rep[Option[String]] = column[Option[String]]("Kafka")
    val zookeeperEnvironment: Rep[String] = column[String]("ZookeeperEnvironment")
    override val isRemoved: Rep[Boolean] = column[Boolean]("IsRemoved", O.Default(false))
    override val createdBy: Rep[Long] = column[Long]("CreatedBy")
    override val createDate: Rep[DateTime] = column[DateTime]("CreateDate")
    override val modifiedBy: Rep[Long] = column[Long]("ModifiedBy")
    override val modifiedDate: Rep[DateTime] = column[DateTime]("ModifiedDate")
    override val isPendingForApproval: Rep[Boolean] = column[Boolean]("IsPendingForApproval", O.Default(false))
    override val attributes: Rep[Map[String, String]] = column[Map[String, String]]("Attributes", O.Length(2147483647, varying = false))
  }
  lazy val  someTable = new TableQuery(tag => new UserDownloadedModulesTable(tag))

} end of trait Ltables
//now use Ltables.someTable as a query
*/