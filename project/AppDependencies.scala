import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val bootstrapVersion = "8.6.0"
  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-backend-play-30" % bootstrapVersion,
  )

  val test = Seq(
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "5.1.0"                 % "test",
    "org.scalatestplus"       %% "scalacheck-1-15"          % "3.2.10.0"              % "test",
    "org.pegdown"             %  "pegdown"                  % "1.6.0"                 % "test",
    "com.eed3si9n"            %% "gigahorse-okhttp"         % "0.7.0"                 % "test",
    "com.vladsch.flexmark"    %  "flexmark-all"             % "0.35.10"               % "test",
    "uk.gov.hmrc"             %% "bootstrap-test-play-30"   % bootstrapVersion        % "test"
  )

}
