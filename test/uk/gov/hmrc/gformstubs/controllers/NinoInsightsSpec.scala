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
import uk.gov.hmrc.gformstubs.model.NinoInsightsRequest

class NinoInsightsSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  "nino insights" should {

    "return 200 with proper values when post with ab123456c" in {
      val request = NinoInsightsRequest(nino = "ab123456c")
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new NinoInsights(stubControllerComponents())
      val result = controller.check()(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "riskScore" -> JsNumber(40),
            "reason"    -> JsString("NINO_NOT_ON_WATCH_LIST")
          )
        )
    }

    "return 200 with proper values when post with ab123456a" in {
      val request = NinoInsightsRequest(nino = "ab123456a")
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new NinoInsights(stubControllerComponents())
      val result = controller.check()(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "riskScore" -> JsNumber(100),
            "reason"    -> JsString("NINO_ON_WATCH_LIST")
          )
        )
    }

    "return 200 with proper values when post with others" in {
      val request = NinoInsightsRequest(nino = "12345678")
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new NinoInsights(stubControllerComponents())
      val result = controller.check()(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "riskScore" -> JsNumber(50),
            "reason"    -> JsString("NINO_NOT_ON_WATCH_LIST")
          )
        )
    }
  }

}
