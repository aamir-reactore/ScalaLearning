
import org.slf4j.LoggerFactory

object Logger extends App {
  val log = LoggerFactory.getLogger(this.getClass.getName)
  log.info("Hello World")
}