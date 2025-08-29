import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin

val appName = "gform-stubs"

ThisBuild / majorVersion := 1
ThisBuild / scalaVersion := "2.13.16"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(
    play.sbt.PlayScala,
    SbtDistributablesPlugin)
  .settings(
    scalafmtOnCompile := true,
    majorVersion := 1,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test
  )
  .settings(PlayKeys.playDefaultPort := 9197)
