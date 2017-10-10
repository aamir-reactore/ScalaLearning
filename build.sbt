import sbt.ExclusionRule

name := "ScalaLearning"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.mysema.scalagen" % "scalagen" % "0.2.2",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
  "joda-time" % "joda-time" % "2.9.9",
  "org.scalatest" % "scalatest-funsuite_2.11" % "3.0.0-SNAP13",
   "org.mockito" % "mockito-all" % "1.10.19",
  "org.apache.kafka" % "kafka_2.11" % "0.10.1.0",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13",
  "org.json4s" %% "json4s-jackson" % "3.4.0",
  "com.typesafe.akka" %% "akka-actor" % "2.4.17",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.17",
  "com.typesafe.akka" %% "akka-stream" % "2.4.17",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.17",
 "com.typesafe.akka" %% "akka-http-core" % "10.0.1"
  /*"org.specs2" % "specs2" % "2.4.6"
  "org.scalatest" %% "scalatest" % "3.0.1"*/
)