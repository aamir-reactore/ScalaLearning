name := "ScalaLearning"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.mysema.scalagen" % "scalagen" % "0.2.2",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "2.0.5",
  "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "2.0.5",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.11.2",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.0",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
  "joda-time" % "joda-time" % "2.9.9",
  "org.scalatest" % "scalatest-funsuite_2.11" % "3.0.0-SNAP13",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.5.4",
  "org.mockito" % "mockito-all" % "1.10.19",
  "org.specs2" %% "specs2" % "2.4.6",
  "org.scalatest" %% "scalatest" % "3.0.1"
)