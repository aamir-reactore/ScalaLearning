/*
import sbt.{Build, Credentials, Defaults, Project, file}
import sbt.Keys._
import sbt._
import com.github.retronym.SbtOneJar._
import Dependencies._

object BuildSettings {

  lazy val common_settings = Defaults.coreDefaultSettings ++
    Seq(/*version := "0.9.0.26-DEV-SNAPSHOT",*/
      scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation"),
      scalaVersion in ThisBuild := "2.11.7",
      updateOptions := updateOptions.value.withLatestSnapshots(false),
      libraryDependencies ++= commonDeps,
      resolvers := Seq(
        "seactore-snapshots" at "http://192.168.1.125:8081/artifactory/libs-snapshot-local",
        "libs-release-local" at "http://192.168.1.125:8081/artifactory/libs-release-local",
        "ext-snapshot-local" at "http://192.168.1.125:8081/artifactory/ext-snapshot-local",
        "ext-release-local" at "http://192.168.1.125:8081/artifactory/ext-release-local",
        "plugins-snapshot-local" at "http://192.168.1.125:8081/artifactory/plugins-snapshot-local",
        "plugins-release-local" at "http://192.168.1.125:8081/artifactory/plugins-release-local",
        "repo1-cache" at "http://192.168.1.125:8081/artifactory/repo1",
        "Maven-cache" at "http://192.168.1.125:8081/artifactory/Maven",
        "ivy-release-cache" at "http://192.168.1.125:8081/artifactory/bintray",
        "Typesafe-cache" at "http://192.168.1.125:8081/artifactory/Typesafe",
        "Sonatype-cache" at "http://192.168.1.125:8081/artifactory/Sonatype",
        "palletops-cache" at "http://192.168.1.125:8081/artifactory/palletops",
        "krasserm-cache" at "http://192.168.1.125:8081/artifactory/krasserm",
        "jcenter-cache" at "http://192.168.1.125:8081/artifactory/jcenter",
        "java.net.m1-cache" at "http://192.168.1.125:8081/artifactory/java.net.m1",
        "Motion-cache" at "http://192.168.1.125:8081/artifactory/Motion",
        "JBoss-cache" at "http://192.168.1.125:8081/artifactory/JBoss",
        "BFil Nexus Releases" at "http://nexus.b-fil.com/nexus/content/repositories/releases/",
        "github-asbachb-releases" at "https://raw.github.com/asbachb/mvn-repo/master/releases"
      ),
      credentials += Credentials("Artifactory Realm",
        "192.168.1.125",
        "admin",
        "b@ng@10r32013"),
      exportJars := true,
      scalacOptions := Seq("-unchecked",
        "-feature",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-deprecation",
        "-encoding",
        "utf8",
        "-Ywarn-adapted-args")
    )
}

object SapEventConnectorBuild extends Build {

  import BuildSettings._

  lazy val connectorCommons = Project(
    id = "connector-commons",
    base = file("connector-commons"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies))

  //in
  lazy val closeEquipmentEvents = Project(
    id = "close-equipment-events",
    base = file("in/close-equipment-events"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val dispatchManager = Project(
    id = "dispatch-manager",
    base = file("in/dispatch-manager"),
    settings = common_settings ++ (libraryDependencies := commonDeps
      ++ integrationDependencies ++ dispatchManagerDeps) ++ oneJarSettings
  ).dependsOn(connectorCommons)

  lazy val dispatchConnector = Project(
    id = "dispatch-connector",
    base = file("in/dispatch-connector"),
    settings = common_settings ++ (libraryDependencies := commonDeps
      ++ integrationDependencies ++ fptsDeps) ++ oneJarSettings
  ).dependsOn(connectorCommons)

  lazy val preventiveMaintenance = Project(
    id = "preventive-maintenance",
    base = file("in/preventive-maintenance"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val materialMaintenance = Project(
    id = "material-maintenance",
    base = file("in/material-maintenance"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val fms = Project(
    id = "fms",
    base = file("in/fms"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings)
    .dependsOn(connectorCommons)
  lazy val lims = Project(
    id = "lims",
    base = file("in/lims"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings)
    .dependsOn(connectorCommons)

  lazy val biometric = Project(
    id = "biometric",
    base = file("in/biometric"),
    settings = common_settings ++ (libraryDependencies := commonDeps
      ++ integrationDependencies ++ biometricDeps) ++ oneJarSettings)
    .dependsOn(connectorCommons)

  //out
  lazy val equipmentMeasuringPoints = Project(
    id = "equipment-measuring-points",
    base = file("out/equipment-measuring-points"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val equipmentEvents = Project(
    id = "equipment-events",
    base = file("out/equipment-events"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val tripData = Project(
    id = "trip-data",
    base = file("out/trip-data"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings)
    .dependsOn(connectorCommons)
  lazy val drillingConnector = Project(
    id = "drilling-data",
    base = file("out/drilling-data"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val blastingConnector = Project(
    id = "blasting-data",
    base = file("out/blasting-data"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings
  ).dependsOn(connectorCommons)
  lazy val fuelConnector = Project(
    id = "fuel-data",
    base = file("out/fuel-data"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings)
    .dependsOn(connectorCommons)
  lazy val limsConnector = Project(
    id = "lims-data",
    base = file("out/lims-data"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings)
    .dependsOn(connectorCommons)
  lazy val sapReport = Project(
    id = "sap-report",
    base = file("out/sap-report"),
    settings = common_settings ++ (libraryDependencies := commonDeps
      ++ integrationDependencies ++ sapReportDeps) ++ oneJarSettings)
    .dependsOn(connectorCommons)
  lazy val crushingData = Project(
    id = "crushing-data",
    base = file("out/crushing-data"),
    settings = common_settings ++ (libraryDependencies := commonDeps ++ integrationDependencies) ++ oneJarSettings)
    .dependsOn(connectorCommons)
  lazy val displayBoard = Project(
    id = "display-board",
    base = file("out/display-board"),
    settings = common_settings ++ (libraryDependencies := commonDeps
      ++ integrationDependencies ++ displayDeps) ++ oneJarSettings)
    .dependsOn(connectorCommons)
}
*/
