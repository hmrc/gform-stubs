/*
 * Copyright 2019 HM Revenue & Customs
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

import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.prop.PropertyChecks
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.libs.json._
import uk.gov.hmrc.gformstubs.generators.DesRegistrationResponseGen
import uk.gov.hmrc.gformstubs.JsResultMatcher
import uk.gov.hmrc.gformstubs.model._

class RegistrationSpec
    extends WordSpec with Matchers with GuiceOneAppPerSuite with JsResultMatcher with PropertyChecks {

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
            None),
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
      val controller = new Registration()
      val result = controller.validator("ind")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result).as[DesRegistrationResponse] should be(Registration.desIndividual)
    }
  }

  "registration validation for org.*" should {
    "return 200 not our office" in {
      val controller = new Registration()
      val result = controller.validator("org")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result).as[DesRegistrationResponse] should be(Registration.desOrganisation)
    }
  }
}
