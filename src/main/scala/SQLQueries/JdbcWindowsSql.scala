

// table creation, you can run DDL by using #execute as same as JDBC

import java.sql.DriverManager
import java.sql.Connection

object TestSql extends App {

  val username = "ss"
  val password = "sss@rt2016"
  val url = "jdbc:sqlserver://192.168.1.125;databaseName=JSW"

  var connection: Connection = _

  try {
    // make the connection
    val driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver" // download it    "com.microsoft.sqlserver" % "sqljdbc4" % "sqljdbc4"

    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)

    // create the statement, and run the select query
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT [Employee Id], In_out_time FROM View_Mines_Row_Data")
    while (resultSet.next()) {
      val host = resultSet.getString("Employee Id")
      val user = resultSet.getString("In_out_time")
      println("Employee Id, In_out_time = " + host + ", " + user)
    }
  } catch {
    case e => e.printStackTrace
  }
  /*  val cmd = s"Select top $getBatchSize * from $getBioMetricTableName" +
      s" where EntryDate > '" + dateTimeInLocalZone.toString("yyyy-MM-dd HH:mm:ss.SSS") + s"' AND [Device IP] IN " +
      s"($allDeviceIPs) order by EntryDate"*/
  connection.close()


}