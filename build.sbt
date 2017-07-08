name := "ScalaLearning"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.mysema.scalagen" % "scalagen" % "0.2.2",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "2.0.5",
  "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "2.0.5",
 "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.11.2"
)