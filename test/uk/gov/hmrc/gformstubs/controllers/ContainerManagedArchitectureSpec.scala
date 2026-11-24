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
import play.api.libs.json.{ JsArray, JsDefined, JsValue, Json }
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

  "podAuthorisationDetails" should {
    val controller: ContainerManagedArchitecture = new ContainerManagedArchitecture(stubControllerComponents())

    def podAuthorisationDetails(podReference: String): Future[Result] =
      controller.podAuthorisationDetails(podReference)(FakeRequest("GET", "/"))

    def assertBusinessFailure(podReference: String, expectedMessage: String) = {
      val result = podAuthorisationDetails(podReference)

      status(result) shouldBe Status.BAD_REQUEST
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) shouldBe Json.obj(
        "businessFailure" -> Json.obj(
          "podReference"      -> podReference,
          "failureCode"       -> "2",
          "validationMessage" -> expectedMessage
        )
      )
    }

    def assertErrorDetail(podReference: String, expectedCode: Int, expectedMessage: String) = {
      val result = podAuthorisationDetails(podReference)

      status(result) shouldBe expectedCode
      contentType(result) shouldBe Some("application/json")
      val errorDetail = contentAsJson(result) \ "errorDetail"
      (errorDetail \ "errorCode").as[Int] shouldBe expectedCode
      (errorDetail \ "errorMessage").as[String] shouldBe expectedMessage
      (errorDetail \ "sourcePDSFaultDetails").as[String] shouldBe
        s"uri=/pds/fta/maintainPodAuthorisationDetails/$podReference/v1"
      (errorDetail \ "timestamp").as[String] should not be empty
    }

    def goodsItems(json: JsValue): List[(String, String)] =
      (json \ "podFtaDetails" \ "goodsItems").as[JsArray].value.toList.map { item =>
        (item \ "hs4Code").as[String] -> (item \ "goodsDescription").as[String]
      }

    "return a 400 business failure for a malformed pod reference" in {
      assertBusinessFailure("POD12345", "Invalid pod authorisation pattern provided")
    }

    "return a 400 business failure when there is no matching authorisation for POD123456789000" in {
      assertBusinessFailure("POD123456789000", "No matching authorisation found using the supplied Pod Reference")
    }

    "return a 400 business failure when the decision is not current for POD123456789001" in {
      assertBusinessFailure("POD123456789001", "Decision not current")
    }

    "return 200 with optional fields omitted for POD123456789002" in {
      val result = podAuthorisationDetails("POD123456789002")

      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      val json = contentAsJson(result)
      (json \ "generalDecisionDetails" \ "emailConsent").as[Boolean] shouldBe false
      (json \ "exporterDetails" \ "ukAddress" \ "name").toOption shouldBe None
      (json \ "exporterDetails" \ "ukAddress" \ "postCode").toOption shouldBe None
      (json \ "exporterDetails" \ "ukAddress" \ "countryCode").toOption shouldBe None
      (json \ "yourDetails" \ "telephoneNumber").toOption shouldBe None
      (json \ "podFtaDetails" \ "exporterActivity").as[String] shouldBe "Production"
      goodsItems(json) shouldBe List("2202" -> "Soft Drinks")
    }

    "return a 404 error detail for POD123456789003" in {
      assertErrorDetail("POD123456789003", Status.NOT_FOUND, "Not Found")
    }

    "return a 500 error detail for POD123456789004" in {
      assertErrorDetail("POD123456789004", Status.INTERNAL_SERVER_ERROR, "An internal error has occurred")
    }

    "return 200 with the full response for any other well-formed pod reference" in {
      val result = podAuthorisationDetails("POD112233445566")

      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      val json = contentAsJson(result)
      (json \ "generalDecisionDetails" \ "emailConsent").as[Boolean] shouldBe true
      (json \ "exporterDetails" \ "eori").as[String] shouldBe "GB523456789586000"
      (json \ "exporterDetails" \ "ukAddress" \ "name").as[String] shouldBe "Rose Cottage"
      (json \ "yourDetails" \ "telephoneNumber").as[String] shouldBe "0161 528467"
      (json \ "podFtaDetails" \ "exporterActivity").as[String] shouldBe "Trading"
      (json \ "podFtaDetails" \ "version").as[Int] shouldBe 1
      (json \ "podFtaDetails" \ "lastPodAuthModifiedDate").as[String] shouldBe "2026-10-05T22:28:58.303182443Z"
      goodsItems(json) shouldBe List("1900" -> "Soft Drinks", "1905" -> "Biscuits")
    }
  }
}
