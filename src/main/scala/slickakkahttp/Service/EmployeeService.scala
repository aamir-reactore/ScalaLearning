package slickakkahttp.Service

import slickakkahttp.Actors.BaseActor
import slickakkahttp.Entities.Employee
import slickakkahttp.Utilities.EmployeeRepository

class EmployeeService(repository: EmployeeRepository) extends BaseActor {
  receiver {
    case e: Employee => sender ! repository.insertItem(e)
  }
}
