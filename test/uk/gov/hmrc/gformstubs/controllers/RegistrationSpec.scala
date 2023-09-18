/*
 * Copyright 2023 HM Revenue & Customs
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

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.libs.json._
import uk.gov.hmrc.gformstubs.generators.DesRegistrationResponseGen
import uk.gov.hmrc.gformstubs.JsResultMatcher
import uk.gov.hmrc.gformstubs.model._
import play.api.http.Status
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class RegistrationSpec
    extends AnyWordSpec with Matchers with GuiceOneAppPerSuite with JsResultMatcher with ScalaCheckPropertyChecks {

  val fakeRequest: FakeRequest[DesRegistrationRequest] = FakeRequest("GET", "/").withBody(null)

  "Successfully parse payload from" should {
    val reads = (payload: String) => implicitly[Reads[DesRegistrationResponse]].reads(Json.parse(payload))

    "des/registration/individual/utr/1111111111" in {
      val payload =
        """|{
           |  "safeId": "XR0000100031105",
           |  "sapNumber": "0100031105",
           |  "isEditable": false,
           |  "isAnAgent": false,
           |  "isAnASAgent": false,
           |  "isAnIndividual": true,
           |  "individual": {
           |    "firstName": "DAVID",
           |    "lastName": "BOWEN"
           |  },
           |  "address": {
           |    "addressLine1": "LINE 1",
           |    "addressLine2": "LINE 2",
           |    "addressLine3": "LINE 3",
           |    "addressLine4": "LINE 4",
           |    "countryCode": "GB",
           |    "postalCode": "M46 0PT"
           |  },
           |  "contactDetails": null
           |}""".stripMargin

      val res = reads(payload)

      res should hasJsSuccess(
        DesRegistrationResponse(
          "XR0000100031105",
          None,
          "0100031105",
          false,
          false,
          false,
          true,
          Individual("DAVID", Some("BOWEN"), None),
          UkAddress("LINE 1", Some("LINE 2"), Some("LINE 3"), Some("LINE 4"), Some("M46 0PT")),
          None
        )
      )
    }

    "des/registration/individual/utr/5119999953" in {

      val payload =
        """|{
           |  "safeId": "XJ0000100032939",
           |  "sapNumber": "0100032939",
           |  "agentReferenceNumber": "QARN0000270",
           |  "isEditable": false,
           |  "isAnAgent": true,
           |  "isAnASAgent": true,
           |  "isAnIndividual": false,
           |  "organisation": {
           |    "organisationName": "SYLVESTER STALLONE9",
           |    "isAGroup": false,
           |    "organisationType": "Not Specified"
           |  },
           |  "address": {
           |    "addressLine1": "18 FAREHAM ROAD",
           |    "addressLine2": "GOSPORT",
           |    "addressLine3": "HAMPSHIRE",
           |    "addressLine4": "ENGLAND",
           |    "countryCode": "GB",
           |    "postalCode": "PO13 9XY"
           |  },
           |  "contactDetails": {
           |    "primaryPhoneNumber": "197 206 3094",
           |    "secondaryPhoneNumber": "01474252551"
           |  }
           |}""".stripMargin

      val res = reads(payload)

      res should hasJsSuccess(
        DesRegistrationResponse(
          "XJ0000100032939",
          Some("QARN0000270"),
          "0100032939",
          false,
          true,
          true,
          false,
          Organisation("SYLVESTER STALLONE9", false, "Not Specified"),
          UkAddress("18 FAREHAM ROAD", Some("GOSPORT"), Some("HAMPSHIRE"), Some("ENGLAND"), Some("PO13 9XY")),
          Some(ContactDetails(Some("197 206 3094"), Some("01474252551"), None, None))
        )
      )
    }
    "des/registration/individual/utr/1695876755" in {

      val payload =
        """|{
           |  "safeId": "XA0000100007634",
           |  "sapNumber": "0100007634",
           |  "isEditable": false,
           |  "isAnAgent": false,
           |  "isAnASAgent": false,
           |  "isAnIndividual": false,
           |  "organisation": {
           |    "organisationName": "RICKEYROUSE",
           |    "isAGroup": false,
           |    "organisationType": "Not Specified"
           |  },
           |  "address": {
           |    "addressLine1": "8, LOWER STREET,",
           |    "addressLine2": "FULHAM-UNDER-WATER",
           |    "addressLine3": "MUCH PETRIFYING",
           |    "countryCode": "ZZ"
           |  },
           |  "contactDetails": {
           |    "primaryPhoneNumber": "0123 456789"
           |  }
           |}""".stripMargin

      val res = reads(payload)

      res should hasJsSuccess(
        DesRegistrationResponse(
          "XA0000100007634",
          None,
          "0100007634",
          false,
          false,
          false,
          false,
          Organisation("RICKEYROUSE", false, "Not Specified"),
          InternationalAddress(
            "8, LOWER STREET,",
            Some("FULHAM-UNDER-WATER"),
            Some("MUCH PETRIFYING"),
            None,
            "ZZ",
            None
          ),
          Some(ContactDetails(Some("0123 456789"), None, None, None))
        )
      )
    }
  }

  "DesRegistrationResponse case class" should {
    "be serialized to json and deserialized from json" in {
      forAll(DesRegistrationResponseGen.desRegistrationResponseGen) { desResponse =>
        DesRegistrationResponse.format.reads(Json.toJson(desResponse)) should hasJsSuccess(desResponse)
      }
    }
  }

  "registration validation for ind.* " should {
    "return 200 our office" in {
      val controller = new Registration(stubControllerComponents())
      val result = controller.validator("ind")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result).as[DesRegistrationResponse] should be(Registration.desIndividual)
    }
  }

  "registration validation for org.*" should {
    "return 200 not our office" in {
      val controller = new Registration(stubControllerComponents())
      val result = controller.validator("org")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result).as[DesRegistrationResponse] should be(Registration.desOrganisation)
    }
  }

  "registration validation should simulate" should {
    "other response than 200 and 400" in {
      val controller = new Registration(stubControllerComponents())
      val result = controller.validator("unauthorized")(fakeRequest)
      status(result) shouldBe Status.UNAUTHORIZED
      contentType(result) shouldBe None
    }
  }

  "registration validation should simulate" should {
    "bad request" in {
      val controller = new Registration(stubControllerComponents())
      val result = controller.validator("fail")(fakeRequest)
      status(result) shouldBe Status.BAD_REQUEST
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result).as[DesRegistrationResponseError] should be(
        DesRegistrationResponseError("INVALID_PAYLOAD", "Submission has not passed validation. Invalid payload.")
      )
    }
  }

  "registration validation should simulate" should {
    "bad request with invalid utr" in {
      val controller = new Registration(stubControllerComponents())
      val result = controller.validator("abc")(fakeRequest)
      status(result) shouldBe Status.BAD_REQUEST
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result).as[DesRegistrationResponseError] should be(
        DesRegistrationResponseError("INVALID_UTR", "Submission has not passed validation. Invalid parameter UTR.")
      )
    }
  }

  "registration validation arn" should {

    "return 200 with proper values when post with AARN1234567" in {
      val controller = new Registration(stubControllerComponents())
      val fakeRequest = FakeRequest("GET", "/")

      val reads = (payload: String) => implicitly[Reads[AgentRecordResponse]].reads(Json.parse(payload))
      val result = controller.validatorArn("AARN1234567")(fakeRequest)
      status(result) shouldBe Status.OK
      val payload = s"""|{
                        |  "contactDetails":{
                        |    "phoneNumber":"01332752856"
                        |  },
                        |  "agencyDetails":{
                        |    "agencyName":"Rogers associates",
                        |    "agencyAddress":{
                        |      "addressLine1":"Plaza 2",
                        |      "addressLine2":"Ironmasters Way",
                        |      "addressLine3":"Telford",
                        |      "addressLine4":"Shropshire",
                        |      "postalCode":"TF3 4NT",
                        |      "countryCode":"GB"
                        |    },
                        |    "agencyEmail":"john@rogers.co.uk"
                        |  }
                        |} """.stripMargin

      reads(payload) should hasJsSuccess(contentAsJson(result).as[AgentRecordResponse])
    }

    "return 200 with proper values when post with BARN1234567" in {
      val controller = new Registration(stubControllerComponents())
      val fakeRequest = FakeRequest("GET", "/")

      val reads = (payload: String) => implicitly[Reads[AgentRecordResponse]].reads(Json.parse(payload))
      val result = controller.validatorArn("BARN1234567")(fakeRequest)
      status(result) shouldBe Status.OK
      val payload = s"""|{
                        |  "contactDetails":{
                        |    "phoneNumber":"01332752856"
                        |  },
                        |  "agencyDetails":{
                        |    "agencyName":"Rogers associates",
                        |    "agencyAddress":{
                        |      "addressLine1":"Plaza 2",
                        |      "addressLine2":"Telford",
                        |      "postalCode":"TF3 4NT",
                        |      "countryCode":"GB"
                        |    },
                        |    "agencyEmail":"john@rogers.co.uk"
                        |  }
                        |}""".stripMargin

      reads(payload) should hasJsSuccess(contentAsJson(result).as[AgentRecordResponse])
    }

    "return 200 with proper values when post with AARN7654321" in {
      val controller = new Registration(stubControllerComponents())
      val fakeRequest = FakeRequest("GET", "/")

      val reads = (payload: String) => implicitly[Reads[AgentRecordResponse]].reads(Json.parse(payload))
      val result = controller.validatorArn("AARN7654321")(fakeRequest)
      status(result) shouldBe Status.OK
      val payload = s"""|{
                        | "contactDetails":{
                        |   "phoneNumber":"01332752856"
                        | },
                        | "agencyDetails":{
                        |   "agencyName":"Rogers USA",
                        |   "agencyAddress":{
                        |     "addressLine1":"Plaza 2",
                        |     "addressLine2":"Sunset Boulevard",
                        |     "addressLine3":"Miami",
                        |     "addressLine4":"Florida",
                        |     "postalCode":"750075",
                        |     "countryCode":"US"
                        |   },
                        |   "agencyEmail":"mike@rogers.co.uk"
                        | }
                        |}""".stripMargin

      reads(payload) should hasJsSuccess(contentAsJson(result).as[AgentRecordResponse])
    }

    "return 200 with proper values when post with BARN7654321" in {
      val controller = new Registration(stubControllerComponents())
      val fakeRequest = FakeRequest("GET", "/")

      val reads = (payload: String) => implicitly[Reads[AgentRecordResponse]].reads(Json.parse(payload))
      val result = controller.validatorArn("BARN7654321")(fakeRequest)
      status(result) shouldBe Status.OK
      val payload = s"""|{
                        | "contactDetails":{
                        |   "phoneNumber":"01332752856"
                        | },
                        | "agencyDetails":{
                        |   "agencyName":"Rogers USA",
                        |   "agencyAddress":{
                        |     "addressLine1":"Plaza 2",
                        |     "addressLine2":"Miami",
                        |     "countryCode":"US"
                        |   },
                        |   "agencyEmail":"mike@rogers.co.uk"
                        | }
                        |}""".stripMargin

      reads(payload) should hasJsSuccess(contentAsJson(result).as[AgentRecordResponse])
    }
  }
}
