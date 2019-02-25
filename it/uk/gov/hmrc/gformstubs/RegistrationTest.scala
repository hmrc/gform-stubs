package uk.gov.hmrc.gformstubs

import org.scalatest.{Matchers, WordSpec}
import play.api.libs.ws.WSClient
import uk.gov.hmrc.integration.ServiceSpec

class RegistrationTest extends WordSpec with Matchers with ServiceSpec  {

  def externalServices: Seq[String] = Seq("datastream", "auth")

  override def additionalConfig: Map[String, _] = Map("auditing.consumer.baseUri.port" -> externalServicePorts("datastream"))

  "This integration test" should {
    "start services via smserver" in {
      val wsClient = app.injector.instanceOf[WSClient]

      val response = wsClient.url(resource("/gform-stubs/registration/organisation/utr/1A")).post("").futureValue
      response.status shouldBe 200
    }
  }
}
