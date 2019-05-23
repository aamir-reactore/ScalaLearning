package eciffocodeexamples.zk.config

import java.io.File

import com.typesafe.config.ConfigFactory
import org.apache.zookeeper._
import org.apache.zookeeper.data.Stat

object ZKConfigHelperObj extends ZKConfigHelper

trait ZKConfigHelper  {

  val MAX_CONNECT_ATTEMPT: Integer = 5


  def createEmptyNodeIfDoesNotExists(name: String): Unit = {

    val zk = createZKConnection(getZkServer)
    createEmptyNodeWithSession(name, zk)
    closeZooKeeper(zk)
  }

  /**
    * This node will create the complete node hierarchy if full path is passed.
    * Should not pass the property here, as it will create it as node, instead of property
    */
  def createEmptyNodeWithSession(name: String, zk: ZooKeeper): Unit = {
    val fullPath = getFullPath(name)
    val fullPathWithoutProperty: String = getNodePathFromFullPath(fullPath)
    val allParentNodes = getNodeHierarchy(fullPathWithoutProperty)
    //sorting the list items by size, to make sure that the parent node is created before the child
    allParentNodes.sortBy(_.length).foreach { node =>
      if (zk.exists(node, false) == null) {
        zk.create(node, "".getBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
      }
    }
  }

  def getNodePathFromFullPath(fullPath: String) = {
    val fullPathWithOutLastDot = if (fullPath.endsWith("/")) fullPath.dropRight(1) else fullPath
    val fullPathWithoutProperty = fullPathWithOutLastDot.substring(0, fullPathWithOutLastDot.lastIndexOf("/"))
    fullPathWithoutProperty
  }

  def createPropertyIfDoesNotExists(name: String, defaultValue: String): Unit = {

    val zk = createZKConnection(getZkServer)
    createPropertyIfDoesNotExists(name, defaultValue, zk)
    closeZooKeeper(zk)
  }

  def createPropertyIfDoesNotExists(name: String, defaultValue: String, zk: ZooKeeper): Unit = {
    val fullPath = getFullPath(name)
    if (zk.exists(fullPath, false) == null) {
      zk.create(fullPath, defaultValue.getBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    }
  }

  def getIndex(name: String) = {
    val zk = createZKConnection(getZkServer)
    var index: Long = 0
    if (nodeExist(name, zk)) {
      index = new String(zk.getData(getFullPath(name), false, new Stat)).toLong
    }
    else {
      insertIndex(name, 0, zk)
      index = new String(zk.getData(getFullPath(name), false, new Stat)).toLong
    }
    closeZooKeeper(zk)
    index
  }


  def getIndex(name: String, seedValue: Long) = {
    val zk = createZKConnection(getZkServer)
    val index = if (nodeExist(name, zk)) {
      new String(zk.getData(getFullPath(name), false, new Stat)).toLong
    }
    else {
      insertIndex(name, seedValue, zk)
      new String(zk.getData(getFullPath(name), false, new Stat)).toLong
    }
    closeZooKeeper(zk)
    index
  }

  def getString(name: String, defaultValue: String) = {
    val zk = createZKConnection(getZkServer)
    val fullPath = getFullPath(name)
    createEmptyNodeWithSession(fullPath,zk)
    createPropertyIfDoesNotExists(name, defaultValue, zk)
    val result = zk.getData(getFullPath(name), false, new Stat)
    closeZooKeeper(zk)
    result
  }

  def getValueWithConnection(name: String, zk: ZooKeeper) = {
    val result = zk.getData(getFullPath(name), false, new Stat)
    result
  }

  def nodeExist(name: String, zk: ZooKeeper): Boolean = {
    val stat = zk.exists(getFullPath(name), false)
    stat != null
  }

  def updateIndex(path: String, value: Long): Unit = {
    val zk = createZKConnection(getZkServer)

    if (nodeExist(path, zk)) {
      zk.setData(getFullPath(path), value.toString.getBytes, -1)
    } else {
      insertIndex(path, value, zk)
    }
    closeZooKeeper(zk)
  }

  def getAllPossibleNodes(nodePropPath: String) = {

    def recursiveMethod(path: String, acc: List[String]): List[String] = {

      val splitRes = path.splitAt(path.lastIndexOf("/"))

      if (splitRes._1.isEmpty) {
        path :: acc //::: List(path)
      } else {
        recursiveMethod(splitRes._1, path :: acc)
      }
    }

    recursiveMethod(nodePropPath, Nil)
  }

  /**
    * This method will give all the parent nodes for the given full path.
    * NOTE: The fullPath should not contain propery. The path should be only for the Nodes
    * e.g: Assume the path as system.service.local.db.username=admin
    * Here, system.service.local.db are the nodes, where as username is a property with value as admin.
    * While, calling this method, only system.service.local.db should be passed.
    * It will give the response as List(system, system.service, system.service.local,system.service.local.db)
    * @param childNodePath
    * @return
    */
  def getNodeHierarchy(childNodePath: String) = {

    def recursiveMethod(path: String, acc: List[String]): List[String] = {

      val splitRes = path.splitAt(path.lastIndexOf("/"))

      if (splitRes._1.isEmpty) {
        path :: acc //::: List(path)
      } else {
        recursiveMethod(splitRes._1, path :: acc)
      }
    }

    recursiveMethod(childNodePath, Nil)
  }

  def createOrUpdateNodesInBatch(url: String, nodesNValues: List[String]) = {
    val zk = createZKConnection(url)
    val props: List[(String, Array[String])] = nodesNValues.map(_.split("=", 2)).map(x => (x(0), x(1).split("=", 2)))

    props.foreach { tup =>
      val nodePropPath = tup._1 + File.separator + tup._2(0)
      val allNodes = getAllPossibleNodes(nodePropPath)
      allNodes.foreach { node => createEmptyNodeWithSession(node, zk) }
      updateIndexWithConnection(nodePropPath, tup._2(1), zk)
    }
    closeZooKeeper(zk)
  }

  def createZKConnection(url: String): ZooKeeper = {
    var connectAttempt: Integer = 0
    val zk: ZooKeeper = new ZooKeeper(url, 200000, new Watcher() {
      def process(event: WatchedEvent) {
        println("Connecting to ZK.")
      }
    })
    while (zk.getState ne ZooKeeper.States.CONNECTED) {
      Thread.sleep(30)
      connectAttempt += 5
      if (connectAttempt eq MAX_CONNECT_ATTEMPT) {
        println("MAX_CONNECT_ATTEMPT")
      }
    }
    zk
  }

  /**
    * This method just checks if the connection to zookeeper can be established. Once it tried to connect, if the attempt failed, it will make the thread sleep
    * for 200 milli-seconds. If the connection is not established in 5 retries, it will return false. Else true. This method is added
    * for launchpad to check if the user selected zookeeper connection is valid
    *
    */
  def checkZKConnection(url: String): Boolean = {
    val zk: ZooKeeper = new ZooKeeper(url, 20000, new Watcher() {
      def process(event: WatchedEvent) {
        println("Connecting to ZK.")
      }
    })
    var connAttempts = 1
    var connStatus = false
    Thread.sleep(20)
    while (connAttempts <= 5 && !connStatus) {
      if (zk.getState == ZooKeeper.States.CONNECTED)
        connStatus = true
      connAttempts += 1
      Thread.sleep(200)

    }
    connStatus
  }

  def closeZooKeeper(zk: ZooKeeper) {
    if (zk != null) {
      zk.close()
    }
  }

  def getZkServer(): String = {
    ConfigHelper.ZK_CONNECTION_STRING
  }

  def getFullPath(path: String): String = {
    val formattedPath = path.trim.replaceAll("\\.", "/")
    val fullPath = "/" + formattedPath
    fullPath.replaceAll("//","/")
  }


  def insertIndex(path: String, value: Long, zk: ZooKeeper): Unit = {
    println("before creating node " + path + " with value " + value)
    // val zk=createZKConnection(getZkServer)
    createEmptyNodeWithSession(path, zk)
    if (!nodeExist(path, zk)) {
      zk.create(getFullPath(path), if (value.toString == null) null else value.toString.getBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    }
  }

  def updateIndexWithString(path: String, value: String): Unit = {
    val zk = createZKConnection(getZkServer)
    if (nodeExist(path, zk)) {
      zk.setData(getFullPath(path), value.getBytes, -1)
    } else {
      //insertIndex(path,value,zk)
      zk.create(getFullPath(path), if (value.toString == null) null else value.toString.getBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    }
    closeZooKeeper(zk)
  }

  def updateIndexWithConnection(path: String, value: String, zk: ZooKeeper): Unit = {
    if (nodeExist(path, zk)) {
      zk.setData(getFullPath(path), value.getBytes, -1)
    } else {
      //insertIndex(path,value,zk)
      zk.create(getFullPath(path), if (value.toString == null) null else value.toString.getBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
    }
  }

}

object ConfigHelper {
  val conf = ConfigFactory.load

  val ZK_CONNECTION_STRING = conf.getString("zookeeper.connectionString")
}