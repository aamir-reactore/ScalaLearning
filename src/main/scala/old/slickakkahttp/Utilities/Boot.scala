/*
package slickakkahttp.Utilities

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Route, RouteConcatenation}
import akka.stream.ActorMaterializer
import slickakkahttp.Actors.EmployeeActor
import slickakkahttp.Controllers.EmployeeController
import slickakkahttp.rest.EmployeeRest

import scala.concurrent.ExecutionContext.Implicits.global

trait EmployeeRestService  extends CORSSupport with EmployeeActor {
  override val contextRoot: String = "training"
  val routes: Route = employeeRoutes

  def employeeRoutes = {
    val availableRoutes = new EmployeeRest(employeeActor, EmployeeController).routes /*~new BookRest().routes*/
    availableRoutes
  }

}

trait RestEndCollection extends EmployeeRestService {
  val availableRoutes: Route = cors(routes)
}

object Boot extends App with RestEndCollection {

  implicit val materializer = ActorMaterializer()

  val r = Http().bindAndHandle(availableRoutes, interface = "0.0.0.0", port = 9000)
  r.map { x => println("Successfully Bound to " + x.localAddress) }.recover { case _ => println("Failed to Bind ") }
  Thread.sleep(5000)

}



import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import scala.concurrent.ExecutionContext.Implicits.global
object WebServer {
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    //implicit val executionContext = system.dispatcher

    val route =
      get {
      /*  pathSingleSlash {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,"<html><body>Hello world!</body></html>"))
        } ~
          path("ping") {
            complete("PONG!")
          } ~ path("crash") {
            sys.error("BOOM!")
          }
      }

    // `route` will be implicitly converted to `Flow` using `RouteResult.route2HandlerFlow`
    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}*/
*/
