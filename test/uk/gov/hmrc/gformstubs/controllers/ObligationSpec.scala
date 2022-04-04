/*
 * Copyright 2022 HM Revenue & Customs
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
import play.api.libs.json.{ JsArray, JsObject, JsString }
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ObligationSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  val fakeRequest = FakeRequest("GET", "/")

  "registration validation for nino" should {
    "return 200 with two periods" in {
      val controller = new Obligation(stubControllerComponents())
      val result = controller.getTaxPeriods("nino", "AA000000A", "AGL")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) should be(
        JsObject(
          Map(
            "obligations" -> JsArray(
              Seq(
                JsObject(
                  Map(
                    "obligationDetails" -> JsArray(
                      Seq(
                        JsObject(
                          Map(
                            "status"                        -> JsString("O"),
                            "inboundCorrespondenceFromDate" -> JsString("2022-04-01"),
                            "inboundCorrespondenceToDate"   -> JsString("2022-04-30"),
                            "inboundCorrespondenceDueDate"  -> JsString("2022-05-15"),
                            "periodKey"                     -> JsString("17B1")
                          )
                        ),
                        JsObject(
                          Map(
                            "status"                        -> JsString("O"),
                            "inboundCorrespondenceFromDate" -> JsString("2022-03-01"),
                            "inboundCorrespondenceToDate"   -> JsString("2022-03-31"),
                            "inboundCorrespondenceDueDate"  -> JsString("2022-04-15"),
                            "periodKey"                     -> JsString("17B2")
                          )
                        ),
                        JsObject(
                          Map(
                            "status"                        -> JsString("O"),
                            "inboundCorrespondenceFromDate" -> JsString("2021-03-01"),
                            "inboundCorrespondenceToDate"   -> JsString("2021-03-31"),
                            "inboundCorrespondenceDueDate"  -> JsString("2021-04-15"),
                            "periodKey"                     -> JsString("17B3")
                          )
                        ),
                        JsObject(
                          Map(
                            "status"                        -> JsString("O"),
                            "inboundCorrespondenceFromDate" -> JsString("2019-12-01"),
                            "inboundCorrespondenceToDate"   -> JsString("2019-12-31"),
                            "inboundCorrespondenceDueDate"  -> JsString("2020-01-15"),
                            "periodKey"                     -> JsString("17B4")
                          )
                        ),
                        JsObject(
                          Map(
                            "status"                        -> JsString("O"),
                            "inboundCorrespondenceFromDate" -> JsString("2018-04-01"),
                            "inboundCorrespondenceToDate"   -> JsString("2019-03-31"),
                            "inboundCorrespondenceDueDate"  -> JsString("2019-04-15"),
                            "periodKey"                     -> JsString("17B5")
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    }
  }

  "registration validation for other than nino" should {
    "return 400" in {
      val controller = new Obligation(stubControllerComponents())
      val result = controller.getTaxPeriods("utr", "", "")(fakeRequest)
      status(result) shouldBe Status.BAD_REQUEST
    }
  }
}
