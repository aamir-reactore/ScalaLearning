import sbt.ExclusionRule

name := "ScalaLearning"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.mysema.scalagen" % "scalagen" % "0.2.2",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
  "joda-time" % "joda-time" % "2.9.9",
  "org.mockito" % "mockito-all" % "1.10.19",
  "org.apache.kafka" % "kafka_2.11" % "0.10.1.0",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13",
  "org.json4s" %% "json4s-jackson" % "3.4.0",
  "com.typesafe.akka" %% "akka-actor" % "2.4.17",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.17",
  "com.typesafe.akka" %% "akka-stream" % "2.4.17",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.17",
  "com.typesafe.akka" %% "akka-http-core" % "10.0.1",
  "com.typesafe.akka" % "akka-cluster-tools_2.11" % "2.4.17",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.4.17",
  "commons-net" % "commons-net" % "3.3",
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.zookeeper" % "zookeeper" % "3.4.9",
  "org.postgresql" % "postgresql" % "9.4.1211",
  "com.typesafe.slick" % "slick_2.11" % "3.2.0-M1",
  "org.json4s" %% "json4s-jackson" % "3.2.11", "joda-time" % "joda-time" % "2.8.2",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11.2",
  //"com.github.cb372" %% "scalacache-old.caching.guava" % "0.9.3",info.cukes
  "com.github.cb372" %% "scalacache-guava" % "0.9.4",
  "net.debasishg" %% "redisclient" % "3.4",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.vividsolutions" % "jts" % "1.13",
 // "org.specs2" %% "specs2" % "2.4.6" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
 // "info.cukes" % "cucumber-scala_2.11" % "1.2.4",
  //"info.cukes" % "cucumber-junit" % "1.2.4",+
  "junit" % "junit" % "4.12"

)