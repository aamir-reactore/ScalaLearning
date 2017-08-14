package actors
/**
  * Source ==> https://danielasfregola.com/2015/04/20/peer-to-many-communication-in-akka/
  */
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.{ActorEventBus, EventBus, LookupClassification}

case class Book1(title:String, authors:List[String])

class AuthorBookBus(author: String) extends EventBus
      with LookupClassification
      with ActorEventBus {
  override type Event = Book1
  override type Classifier = Boolean

  override protected def mapSize(): Int = 2

  override protected def classify(book: Book1) = book.authors.contains(author)

  /**
    * called in loop from bus.publish(book) and classified first for filters.
    */
  override protected def publish(event: Book1, subscriber: Subscriber) = subscriber ! event //  type Subscriber = ActorRef
}

class Book1Publisher(bus: AuthorBookBus) extends Actor {
  override def receive = {
    case book: Book1 =>
      println(s"Yeah! Publishing a new book: $book")
      bus.publish(book)
  }
}

class Book1Subscriber extends Actor {
  def receive = {
    case book: Book1 => println(s"My name is ${self.path.name} and I have received a new book: $book")
  }
}

object EventBusTest extends App {
  implicit val system = ActorSystem("publisher-subscribers-example")
  val author = "Author"

  val authorBookBus = new AuthorBookBus(author)
  val bookPublisher = system.actorOf(Props(new Book1Publisher(authorBookBus)), name = "book-publisher")

  val subscriber1 = system.actorOf(Props[Book1Subscriber], name = "subscriber-1")
  val subscriber2 = system.actorOf(Props[Book1Subscriber], name = "subscriber-2")

  //Assume that now a subscriber wants to receive books for a specific author
  authorBookBus.subscribe(subscriber1,true) //i.e.subscriber1 will receive all the books where one of the authors is "Author"
  authorBookBus.subscribe(subscriber2,false)//i.e.subscriber2 will receive all the books where one of the authors is not "Author"

  bookPublisher ! Book1(title = "A book title", authors = List(author, "Another Author"))

  println(s">>>>${Thread.sleep(200)}")


  bookPublisher ! Book1(title = "Another book title", authors = List("Another Author"))
}
