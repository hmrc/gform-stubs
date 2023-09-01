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
import play.api.libs.json.{ JsObject, JsString }
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.test.Helpers.{ contentAsJson, status, stubControllerComponents }
import uk.gov.hmrc.gformstubs.model.{ BankAccount, BankAccountRequest }

class BankAccountDetailsSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  "bankAccount verify/business" should {

    "return 200 with proper values when post with 206705/86563611" in {
      val request = BankAccountRequest(BankAccount(sortCode = "206705", accountNumber = "86563611"))
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountDetails(stubControllerComponents())
      val result = controller.businessBankAccountExistence(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "accountNumberIsWellFormatted"             -> JsString("yes"),
            "accountExists"                            -> JsString("yes"),
            "nonStandardAccountDetailsRequiredForBacs" -> JsString("no"),
            "sortCodeBankName"                         -> JsString("Nationwide"),
            "nameMatches"                              -> JsString("no"),
            "accountName"                              -> JsString(""),
            "sortCodeIsPresentOnEISCD"                 -> JsString("yes"),
            "sortCodeSupportsDirectDebit"              -> JsString(""),
            "sortCodeSupportsDirectCredit"             -> JsString(""),
            "iban"                                     -> JsString("")
          )
        )
    }

    "return 200 with proper values when post with others" in {
      val request = BankAccountRequest(BankAccount(sortCode = "333333", accountNumber = "12345678"))
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountDetails(stubControllerComponents())
      val result = controller.businessBankAccountExistence(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "accountNumberIsWellFormatted"             -> JsString("no"),
            "accountExists"                            -> JsString("no"),
            "nonStandardAccountDetailsRequiredForBacs" -> JsString("no"),
            "sortCodeBankName"                         -> JsString(""),
            "nameMatches"                              -> JsString("no"),
            "accountName"                              -> JsString(""),
            "sortCodeIsPresentOnEISCD"                 -> JsString(""),
            "sortCodeSupportsDirectDebit"              -> JsString(""),
            "sortCodeSupportsDirectCredit"             -> JsString(""),
            "iban"                                     -> JsString("")
          )
        )
    }
  }

  "bankAccount verify/personal" should {

    "return 200 with proper values when post with 206705/44311611" in {
      val request = BankAccountRequest(BankAccount(sortCode = "206705", accountNumber = "44311611"))
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountDetails(stubControllerComponents())
      val result = controller.personalBankAccountExistence(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "accountNumberIsWellFormatted"             -> JsString("yes"),
            "accountExists"                            -> JsString("yes"),
            "nonStandardAccountDetailsRequiredForBacs" -> JsString("no"),
            "sortCodeBankName"                         -> JsString("BARCLAYS BANK UK PLC"),
            "nameMatches"                              -> JsString("yes"),
            "accountName"                              -> JsString(""),
            "sortCodeIsPresentOnEISCD"                 -> JsString("yes"),
            "sortCodeSupportsDirectDebit"              -> JsString("no"),
            "sortCodeSupportsDirectCredit"             -> JsString("no"),
            "iban"                                     -> JsString("GB21BARC20670544311611")
          )
        )
    }

    "return 200 with proper values when post with others" in {
      val request = BankAccountRequest(BankAccount(sortCode = "333333", accountNumber = "12345678"))
      val fakeRequest = FakeRequest("POST", "/").withBody(request)
      val controller = new BankAccountDetails(stubControllerComponents())
      val result = controller.businessBankAccountExistence(fakeRequest)
      status(result) shouldBe Status.OK
      contentAsJson(result) shouldBe
        JsObject(
          Map(
            "accountNumberIsWellFormatted"             -> JsString("no"),
            "accountExists"                            -> JsString("no"),
            "nonStandardAccountDetailsRequiredForBacs" -> JsString("no"),
            "sortCodeBankName"                         -> JsString(""),
            "nameMatches"                              -> JsString("no"),
            "accountName"                              -> JsString(""),
            "sortCodeIsPresentOnEISCD"                 -> JsString(""),
            "sortCodeSupportsDirectDebit"              -> JsString(""),
            "sortCodeSupportsDirectCredit"             -> JsString(""),
            "iban"                                     -> JsString("")
          )
        )
    }
  }

}
