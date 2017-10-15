/*
import sbt.{Build, Credentials, Defaults, Project, file, _}
import sbt.Keys._
import com.github.retronym.SbtOneJar._
object BuildSettings {
  lazy val common_settings = Defaults.coreDefaultSettings ++
    Seq(
      scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation"),
      scalaVersion in ThisBuild := "2.11.7",

      libraryDependencies ++= Seq(
        "org.apache.commons" % "commons-csv" % "1.1"
      ),
      resolvers := Seq(
      ),
      credentials += Credentials("nameofartif", "ip", "u/n", "p/w"),
      scalacOptions := Seq(
        "-unchecked",
        "-feature",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-deprecation",
        "-encoding", "utf8",
        "-Ywarn-adapted-args")
    )

}
object ProjectNameBuild extends Build {
  import BuildSettings._

  lazy val assetReadingConnector = Project(id = "projectname-api", base = file("projectname-api"), settings = common_settings /*++ oneJarSettings*/)

}*/
