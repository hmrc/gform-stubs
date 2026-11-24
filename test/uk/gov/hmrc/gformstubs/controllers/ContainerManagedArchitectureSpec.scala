/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.gformstubs.controllers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.libs.json.{ JsDefined, JsNull, Json }
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{ contentAsJson, contentType, defaultAwaitTimeout, status, stubControllerComponents }

import scala.concurrent.Future

class ContainerManagedArchitectureSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {
  val fakeRequest = FakeRequest("POST", "/")

  "postVatParcelsC2C" should {
    "return 200 with simple response" in {
      val controller: ContainerManagedArchitecture = new ContainerManagedArchitecture(stubControllerComponents())
      val result: Future[Result] = controller.postVatParcelsC2C()(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      (contentAsJson(result) \ "success" \ "processingDate") match {
        case JsDefined(_) => assert(true)
        case _            => assert(false)
      }
    }
  }

  "verifyPodStatus" should {
    val controller: ContainerManagedArchitecture = new ContainerManagedArchitecture(stubControllerComponents())

    "return a not-found match response for POD123456789000" in {
      val result = controller.verifyPodStatus("POD123456789000")(FakeRequest("GET", "/"))

      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) shouldBe Json.obj(
        "matchFound" -> false,
        "matchNotFoundReason" ->
          "No authorisation was found with an authorisation reference number matching supplied Pod Reference POD123456789000"
      )
    }

    "return an invalid registration response for POD123456789001" in {
      val result = controller.verifyPodStatus("POD123456789001")(FakeRequest("GET", "/"))

      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe Json.obj(
        "matchFound"                  -> true,
        "exporterCompanyName"         -> "Acme Limited",
        "podReference"                -> "POD123456789001",
        "currentRegistrationStatus"   -> "NOT_VALID",
        "registrationValidityPeriods" -> Json.arr()
      )
    }

    "return a valid registration response for POD123456789002" in {
      val result = controller.verifyPodStatus("POD123456789002")(FakeRequest("GET", "/"))

      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe Json.obj(
        "matchFound"                -> true,
        "exporterCompanyName"       -> "Acme Incorporated",
        "podReference"              -> "POD123456789002",
        "currentRegistrationStatus" -> "VALID",
        "registrationValidityPeriods" -> Json.arr(
          Json.obj("status" -> "VALID", "validFrom" -> "2026-08-01", "validTo" -> JsNull)
        )
      )
    }

    "return the default valid registration response for another well-formed reference" in {
      val result = controller.verifyPodStatus("POD123456789003")(FakeRequest("GET", "/"))

      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe Json.obj(
        "matchFound"                -> true,
        "exporterCompanyName"       -> "Acme Partners & Co",
        "podReference"              -> "POD123456789003",
        "currentRegistrationStatus" -> "VALID",
        "registrationValidityPeriods" -> Json.arr(
          Json.obj("status" -> "VALID", "validFrom"     -> "2026-01-01", "validTo" -> "2026-04-24"),
          Json.obj("status" -> "NOT_VALID", "validFrom" -> "2026-06-01", "validTo" -> "2026-08-31"),
          Json.obj("status" -> "VALID", "validFrom"     -> "2026-09-01", "validTo" -> JsNull)
        )
      )
    }

    "return a backend error for a malformed reference and include its correlation ID" in {
      val correlationId = "test-correlation-id"
      val request = FakeRequest("GET", "/").withHeaders("X-Correlation-Id" -> correlationId)
      val result = controller.verifyPodStatus("invalid")(request)

      status(result) shouldBe Status.INTERNAL_SERVER_ERROR
      contentType(result) shouldBe Some("application/json")
      val errorDetail = contentAsJson(result) \ "errorDetail"
      (errorDetail \ "errorCode").as[String] shouldBe "500"
      (errorDetail \ "errorMessage").as[String] should include("verifystatus/invalid")
      (errorDetail \ "source").as[String] shouldBe "Backend"
      ((errorDetail \ "sourceFaultDetail" \ "businessFailure" \ "podReference").as[String]) shouldBe "invalid"
      ((errorDetail \ "sourceFaultDetail" \ "businessFailure" \ "failureCode").as[String]) shouldBe "2"
      ((errorDetail \ "sourceFaultDetail" \ "businessFailure" \ "validationMessage").as[String]) should include(
        "does not conform"
      )
      (errorDetail \ "correlationId").as[String] shouldBe correlationId
    }
  }
}
