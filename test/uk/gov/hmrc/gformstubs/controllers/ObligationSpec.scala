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
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.libs.json.{ JsArray, JsObject, JsString }
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ObligationSpec extends WordSpec with Matchers with GuiceOneAppPerSuite {

  val fakeRequest = FakeRequest("GET", "/")

  "registration validation for nino" should {
    "return 200 with two periods" in {
      val controller = new Obligation()
      val result = controller.getTaxPeriods("nino", "AA000000A", "AGL")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) should be(
        JsObject(Map("obligations" -> JsObject(Map("taxPeriods" -> JsArray(Seq(
          JsObject(Map(
            "inboundCorrespondenceFromDate" -> JsString("2019-05-23"),
            "inboundCorrespondenceToDate"   -> JsString("b"),
            "periodKey"                     -> JsString("c")
          )),
          JsObject(Map(
            "inboundCorrespondenceFromDate" -> JsString("2019-06-24"),
            "inboundCorrespondenceToDate"   -> JsString("2019-09-24"),
            "periodKey"                     -> JsString("#001")
          ))
        )))))))
    }
  }

  "registration validation for other than nino" should {
    "return 400" in {
      val controller = new Obligation()
      val result = controller.getTaxPeriods("utr", "", "")(fakeRequest)
      status(result) shouldBe Status.BAD_REQUEST
    }
  }
}
