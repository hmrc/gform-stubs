import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-backend-play-28" % "7.13.0",
    "org.scalacheck"          %% "scalacheck"                % "1.15.4"
  )

  val test = Seq(
    "org.scalatestplus.play" %% "scalatestplus-play"        % "5.1.0"                 % "test, it",
    "org.scalatestplus"       %% "scalacheck-1-15"          % "3.2.10.0"              % "test, it",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test, it",
    "com.eed3si9n"            %% "gigahorse-okhttp"         % "0.7.0"                 % "test, it",
    "com.vladsch.flexmark"    %  "flexmark-all"             % "0.35.10"               % "test, it",
    "uk.gov.hmrc"             %% "bootstrap-test-play-28"   % "7.13.0"                % "test, it"
  )

}
