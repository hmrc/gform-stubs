import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-play-26"        % "1.3.0",
    "org.scalacheck"          %% "scalacheck"               % "1.14.0"
  )

  val test = Seq(
    "org.scalatest"           %% "scalatest"                % "3.0.5"                 % "test",
    "com.typesafe.play"       %% "play-test"                % current                 % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "uk.gov.hmrc"             %% "service-integration-test" % "0.12.0-play-26"        % "test, it",
    "com.eed3si9n"            %% "gigahorse-okhttp"         % "0.3.1"                 % "test, it"
  )

}
