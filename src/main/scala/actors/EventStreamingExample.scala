package actors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Source ==> https://danielasfregola.com/2015/04/20/peer-to-many-communication-in-akka/
  */
case class Book(title:String, authors:List[String])

class BookPublisher extends Actor {
  def receive = {
    case book:Book =>
      println(s"Yeah! Publishing a new Book: $book")
      context.system.eventStream.publish(book)
  }
}

class BookSubscriber extends Actor {
  override def preStart():Unit = context.system.eventStream.subscribe(self,classOf[Book])
  def receive = {
    case book:Book => println(s"My name is ${self.path.name} a I have received a book: $book")
  }
}

object EventStreamTest extends App {
  implicit val system = ActorSystem("publisher-subscriber-example")

  val bookPublisher = system.actorOf(Props[BookPublisher],name="book-publisher")

  val subscriber1 = system.actorOf(Props[BookSubscriber],name="subscriber-1")
  val subscriber2 = system.actorOf(Props[BookSubscriber],name="subscriber-2")

  bookPublisher ! Book(title = "A book title", authors = List("Author", "Another author"))
  println(s">>>>${Thread.sleep(200)}")
  system.eventStream.unsubscribe(subscriber2, classOf[Book])

  bookPublisher ! Book(title = "Another book title", authors = List("Another author"))
}

