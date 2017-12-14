import com.typesafe.config.ConfigFactory

object GetAppConfProps extends App {
 val res = ConfigFactoryHelper.config.getString("in.something")
}
object ConfigFactoryHelper {
  val config = ConfigFactory.load()
}