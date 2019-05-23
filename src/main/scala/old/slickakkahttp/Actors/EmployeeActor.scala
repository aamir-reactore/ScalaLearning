package slickakkahttp.Actors

import akka.actor.{ActorSystem, Props}
import slickakkahttp.Service.EmployeeService
import slickakkahttp.Utilities.ImplEmployeeRepository


trait EmployeeActor extends GeneralActors with CoreActorSystem with RootSupervisorHelper {

   override implicit def system: ActorSystem = ActorSystemContainer.system

  val employeeActor = createRouters(Props(classOf[EmployeeService], ImplEmployeeRepository), availableProcessors)

}