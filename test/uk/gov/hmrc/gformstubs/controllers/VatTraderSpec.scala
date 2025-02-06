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

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.libs.json.{ JsBoolean, JsObject, JsString }
import play.api.test.FakeRequest
import play.api.test.Helpers._

class VatTraderSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  val fakeRequest = FakeRequest("GET", "/")

  "VAT Trader" should {
    "return 200 with details for getTrader(valid VRN)" in {
      val controller = new VatTrader(stubControllerComponents())
      val result = controller.getTrader("123456789")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) should be(
        JsObject(
          Map(
            "links" -> JsObject(
              Map(
                "designatoryDetails" -> JsString("/vat/trader/123456789/designatory-details"),
                "accountSummary"     -> JsString("/vat/trader/123456789/account-summary")
              )
            ),
            "hasDesignatoryDetails" -> JsBoolean(true),
            "hasAccountSummary"     -> JsBoolean(false)
          )
        )
      )
    }
    "return not found with details for getTrader(invalid VRN)" in {
      val controller = new VatTrader(stubControllerComponents())
      val result = controller.getTrader("123123123")(fakeRequest)
      status(result) shouldBe Status.NOT_FOUND
      contentAsJson(result) should be(
        JsObject(
          Map(
            "correlationid" -> JsString("f379093d-418f-4e17-ab9f-b712b1b427e6")
          )
        )
      )
    }

    "return 200 with details for getDesignatoryDetails(valid VRN)" in {
      val controller = new VatTrader(stubControllerComponents())
      val result = controller.getDesignatoryDetails("123456789")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) should be(
        JsObject(
          Map(
            "name" -> JsObject(
              Map(
                "traderName1" -> JsString("Example Trader")
              )
            ),
            "address" -> JsObject(
              Map(
                "addressLine1" -> JsString("123 Example Street"),
                "addressLine2" -> JsString("Example District"),
                "addressLine3" -> JsString("Example City"),
                "addressLine4" -> JsString("Example County"),
                "addressLine5" -> JsString("Example Country"),
                "postcode" -> JsString("EX4 3PL")
              )
            ),
            "contact" -> JsObject(
              Map(
                "telephone" -> JsObject(
                  Map(
                    "daytime" -> JsString("01234567890"),
                    "mobile" -> JsString("07123456789")
                  )
                ),
                "email" -> JsObject(
                  Map(
                    "email" -> JsString("example@example.com")
                  )
                )
              )
            )
          )
        )
      )
    }
    "return not found with details for getDesignatoryDetails(invalid VRN)" in {
      val controller = new VatTrader(stubControllerComponents())
      val result = controller.getDesignatoryDetails("123123123")(fakeRequest)
      status(result) shouldBe Status.NOT_FOUND
      contentAsJson(result) should be(
        JsObject(
          Map(
            "correlationid" -> JsString("f379093d-418f-4e17-ab9f-b712b1b427e6")
          )
        )
      )
    }
  }

}
