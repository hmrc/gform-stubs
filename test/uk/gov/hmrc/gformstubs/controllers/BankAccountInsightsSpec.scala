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

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.libs.json.{ JsNumber, JsObject, JsString }
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.gformstubs.model.BankAccountInsightsRequest

class BankAccountInsightsSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  "bankAccount insights" should {

    "return 200 with proper values when post with 111111/12345678" in {
      val request = BankAccountInsightsRequest(sortCode = "111111", accountNumber = "12345678")
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountInsights(stubControllerComponents())
      val result = controller.check()(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "riskScore" -> JsNumber(40),
            "reason"    -> JsString("ACCOUNT_NOT_ON_WATCH_LIST")
          )
        )
    }

    "return 200 with proper values when post with 222222/12345678" in {
      val request = BankAccountInsightsRequest(sortCode = "222222", accountNumber = "12345678")
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountInsights(stubControllerComponents())
      val result = controller.check()(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "riskScore" -> JsNumber(100),
            "reason"    -> JsString("ACCOUNT_ON_WATCH_LIST")
          )
        )
    }

    "return 200 with proper values when post with others" in {
      val request = BankAccountInsightsRequest(sortCode = "333333", accountNumber = "12345678")
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountInsights(stubControllerComponents())
      val result = controller.check()(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "riskScore" -> JsNumber(50),
            "reason"    -> JsString("ACCOUNT_ON_WATCH_LIST")
          )
        )
    }
  }

}
