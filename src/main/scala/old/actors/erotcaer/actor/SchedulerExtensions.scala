 //
//import akka.actor.{ActorRef, ActorSystem}
 //import org.joda.time.DateTime
//import org.slf4j.LoggerFactory
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.duration._

//
//object SchedulerExtensions {
//
//  val schedulerExtensionsLogger = LoggerFactory.getLogger("SchedulerExtensions")
//
//  import SchedulePeriod._
//
//  implicit class SchedulerExtensions(system: ActorSystem) {
//
//    def scheduleInTime(period: SchedulePeriod, receiver: ActorRef, msg: Any, plusDuration: FiniteDuration = -100.milliseconds) = {
//      internalSchedule(period, receiver, msg, plusDuration, scheduleOnce = false)
//    }
//
//    def schedule(period: SchedulePeriod, receiver: ActorRef, msg: Any, plusDuration: FiniteDuration = 0.seconds) = {
//      internalSchedule(period, receiver, msg, plusDuration, scheduleOnce = false)
//    }
//
//    def scheduleOnce(period: SchedulePeriod, receiver: ActorRef, msg: Any, plusDuration: FiniteDuration = 0.seconds) = {
//      internalSchedule(period: SchedulePeriod, receiver: ActorRef, msg: Any, plusDuration, scheduleOnce = true)
//    }
//
//    private def internalSchedule(period: SchedulePeriod, receiver: ActorRef, msg: Any, plusDuration: FiniteDuration, scheduleOnce: Boolean = false) = {
//      val runnable = new Runnable {
//        override def run = {
//          execute(period, receiver, msg, plusDuration, scheduleOnce)
//        }
//      }
//      system.scheduler.scheduleOnce(getTheNextTimeInMillis(period, plusDuration), runnable)
//    }
//
//    //@tailrec
//    private def execute(period: SchedulePeriod, receiver: ActorRef, msg: Any, plusDuration: FiniteDuration, isOnce: Boolean): Unit = {
//      schedulerExtensionsLogger.info("Invoking the scheduler for "+period)
//      receiver ! msg
//      if (!isOnce) {
//        //If it is not once, then schedule again with another runnable
//        val runnable = new Runnable {
//          override def run() = {
//            execute(period, receiver, msg, plusDuration, isOnce)
//          }
//        }
//        system.scheduler.scheduleOnce(getTheNextTimeInMillis(period, plusDuration), runnable)
//      }
//    }
//
//    private def getTheNextTimeInMillis(period: SchedulePeriod, plusDuration: FiniteDuration) = {
//      val sleepTime = if (plusDuration.toMillis < 0) {
//        (0 - plusDuration.toMillis) + 1
//      }
//      else 1
//      Thread.sleep(sleepTime) // Just to make sure we pass the current window
//      val now = DateTime.now()
//      val time = period match {
//        case EveryHour   => now.withTimeAtEndOfHour
//        case EveryDay    => now.withTimeAtEndOfDay
//        case EveryWeek   => now.withTimeAtEndOfWeek
//        case EveryMonth  => now.withTimeAtEndOfMonth
//        case EveryMinute => now.plusSeconds(5)
//      }
//      (time.getMillis - now.getMillis).milliseconds + plusDuration
//    }
//  }
//
//}
//
//trait SchedulePeriod
//
//object SchedulePeriod {
//
//  case object EveryHour extends SchedulePeriod
//
//  case object EveryDay extends SchedulePeriod
//
//  case object EveryWeek extends SchedulePeriod
//
//  case object EveryMonth extends SchedulePeriod
//
//  case object EveryShift extends SchedulePeriod
//
//  //TODO: get from shifts
//  case object EveryMinute extends SchedulePeriod
//
//}
