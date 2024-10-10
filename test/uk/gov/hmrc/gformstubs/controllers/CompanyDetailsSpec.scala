/*
 * Copyright 2024 HM Revenue & Customs
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
import play.api.libs.json.{ JsArray, JsBoolean, JsObject, JsString }
import play.api.test.FakeRequest
import play.api.test.Helpers._

class CompanyDetailsSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  val fakeRequest = FakeRequest("GET", "/")

  "Company details" should {
    "return 200 with details for getCompanyDetails(valid CRN)" in {
      val controller = new CompanyDetails(stubControllerComponents())
      val result = controller.getCompanyDetails("11111111")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) should be(
        JsObject(
          Map(
            "can_file"              -> JsBoolean(false),
            "company_name"          -> JsString("Acme Limited"),
            "company_number"        -> JsString("11111111"),
            "company_status"        -> JsString("active"),
            "company_status_detail" -> JsString(""),
            "registered_office_address" -> JsObject(
              Map(
                "address_line_1" -> JsString("1 Main Street"),
                "address_line_2" -> JsString("Office 234"),
                "care_of"        -> JsString(""),
                "country"        -> JsString("UK"),
                "locality"       -> JsString("London"),
                "po_box"         -> JsString(""),
                "postal_code"    -> JsString("E14 5AB"),
                "premises"       -> JsString(""),
                "region"         -> JsString("London")
              )
            ),
            "undeliverable_registered_office_address" -> JsBoolean(false)
          )
        )
      )
    }
    "return not found with details for getCompanyDetails(invalid CRN)" in {
      val controller = new CompanyDetails(stubControllerComponents())
      val result = controller.getCompanyDetails("12312312")(fakeRequest)
      status(result) shouldBe Status.NOT_FOUND
    }
    "return 200 with details for getCompanyOfficers(valid CRN)" in {
      val controller = new CompanyDetails(stubControllerComponents())
      val result = controller.getCompanyOfficers("11111111")(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      contentAsJson(result) should be(
        JsObject(
          Map(
            "items" -> JsArray(
              Seq(
                JsObject(
                  Map(
                    "officer_role" -> JsString("director")
                  )
                ),
                JsObject(
                  Map(
                    "officer_role" -> JsString("director")
                  )
                ),
                JsObject(
                  Map(
                    "officer_role" -> JsString("director")
                  )
                ),
                JsObject(
                  Map(
                    "officer_role" -> JsString("director")
                  )
                ),
                JsObject(
                  Map(
                    "officer_role" -> JsString("director")
                  )
                ),
                JsObject(
                  Map(
                    "officer_role" -> JsString("secretary")
                  )
                )
              )
            )
          )
        )
      )
    }
    "return not found with details for getCompanyOfficers(invalid CRN)" in {
      val controller = new CompanyDetails(stubControllerComponents())
      val result = controller.getCompanyOfficers("12312312")(fakeRequest)
      status(result) shouldBe Status.NOT_FOUND
    }
  }
}
