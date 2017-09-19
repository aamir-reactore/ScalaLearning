package actors.salmakhater

import actors.salmakhater.WatcherTest.Counter
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, MustMatchers}

class CounterSpec extends TestKit(ActorSystem("test-system"))
  with FlatSpecLike
  with BeforeAndAfterAll
  with MustMatchers
  with ImplicitSender {

  import scala.concurrent.duration._

  override def afterAll = {
    TestKit.shutdownActorSystem(system)
  }

  "Counter Actor" should "handle GetCount message with using TestProbe" in {
    val sender = TestProbe()
    val counter = system.actorOf(Props[Counter])
    sender.send(counter, Counter.Inc(1))
    sender.send(counter, GetCount)
    val state = sender.expectMsgType[Int]
    state must equal(1)
  }
  "it" should "handle increment message" in {
    val counter = system.actorOf(Props[Counter])
    counter ! Counter.Inc(1)
    expectNoMsg(1.second)
  }
  // using ImplicitSender to send GetCount message
  "it" should "handle GetCount message" in {
    val counter = system.actorOf(Props[Counter])
    counter ! GetCount
    expectMsg(0)
  }

}