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

import play.api.libs.json.Json
import play.api.mvc.{ AbstractController, ControllerComponents }
import uk.gov.hmrc.gformstubs.model.{ AccountDetails, BankAccount, BankAccountRequest }

import javax.inject.{ Inject, Singleton }

@Singleton
class BankAccountDetails @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  // pair of (sortCode, accountNumber)
  val businessBankAccountMap: Map[BankAccount, AccountDetails] = Map(
    BankAccount("609593", "96113600") -> AccountDetails("yes", "yes", "Skipton Building Society", "indeterminate", ""),
    BankAccount("206705", "86473611") -> AccountDetails("yes", "no", "Nationwide", "yes", ""),
    BankAccount("206705", "86563611") -> AccountDetails("yes", "no", "Nationwide", "no", ""),
    BankAccount("206705", "76523611") -> AccountDetails("yes", "no", "Nationwide", "partial", "Acme Limited"),
    BankAccount("206705", "11115555") -> AccountDetails("no", "indeterminate", "", "indeterminate", ""),
    BankAccount("206705", "11116666") -> AccountDetails("inapplicable", "indeterminate", "", "indeterminate", ""),
    BankAccount("206705", "11117777") -> AccountDetails(
      "indeterminate",
      "indeterminate",
      "Barclays",
      "indeterminate",
      ""
    )
  )
  // pair of (sortCode, accountNumber)
  val personalBankAccountMap: Map[BankAccount, AccountDetails] = Map(
    BankAccount("609593", "91661500") -> AccountDetails("yes", "yes", "Skipton Building Society", "indeterminate", ""),
    BankAccount("206705", "44311611") -> AccountDetails("yes", "no", "Nationwide", "yes", ""),
    BankAccount("206705", "44344611") -> AccountDetails("yes", "no", "Nationwide", "no", ""),
    BankAccount("206705", "44355611") -> AccountDetails("yes", "no", "Nationwide", "partial", "John Smith"),
    BankAccount("206705", "11112222") -> AccountDetails("no", "indeterminate", "", "indeterminate", ""),
    BankAccount("206705", "11113333") -> AccountDetails("inapplicable", "indeterminate", "", "indeterminate", ""),
    BankAccount("206705", "11114444") -> AccountDetails(
      "indeterminate",
      "indeterminate",
      "Barclays",
      "indeterminate",
      ""
    )
  )

  def businessBankAccountExistence = Action(parse.json[BankAccountRequest]) { request =>
    val account = request.body.account
    val accountDetailsOpt = businessBankAccountMap.get(BankAccount(account.sortCode, account.accountNumber))
    accountDetailsOpt match {
      case Some(accountDetails) =>
        Ok(Json.toJson(accountDetails))
      case None =>
        val unknownAccountDetails = AccountDetails("no", "no", "unknown", "unknown", "unknown")
        Ok(Json.toJson(unknownAccountDetails))
    }
  }

  def personalBankAccountExistence = Action(parse.json[BankAccountRequest]) { request =>
    val account = request.body.account
    val accountDetailsOpt = personalBankAccountMap.get(BankAccount(account.sortCode, account.accountNumber))
    accountDetailsOpt match {
      case Some(accountDetails) =>
        Ok(Json.toJson(accountDetails))
      case None =>
        val unknownAccountDetails = AccountDetails("no", "no", "unknown", "unknown", "unknown")
        Ok(Json.toJson(unknownAccountDetails))
    }
  }

}
