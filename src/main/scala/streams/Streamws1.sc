import akka.actor._
import akka.stream._

implicit val system = ActorSystem("TestSystem")
implicit val materializer = ActorMaterializer()
import akka.stream.scaladsl.Source
val s1 = Source.empty
val s2 = Source.single("single element")
val s3 = Source(1 to 3)
s2 runForeach println

