import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc" %% "bootstrap-backend-play-27" % "3.2.0",
    "org.scalacheck"          %% "scalacheck"               % "1.14.3"
  )

  val test = Seq(
    "com.typesafe.play"       %% "play-test"                % current                 % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "uk.gov.hmrc" %% "service-integration-test" % "0.13.0-play-27" % "test, it",
    "com.eed3si9n"            %% "gigahorse-okhttp"         % "0.3.1"                 % "test, it"
  )

}
