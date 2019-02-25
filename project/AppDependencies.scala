import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-play-25"        % "4.9.0")

  val test = Seq(
    "org.scalatest"           %% "scalatest"                % "3.0.5"                 % "test",
    "com.typesafe.play"       %% "play-test"                % current                 % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "uk.gov.hmrc"             %% "service-integration-test" % "0.5.0-play-25"         % "test, it",
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "2.0.1"                 % "test, it")
}
