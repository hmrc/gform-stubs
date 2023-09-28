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
    BankAccount("609593", "96113600") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "yes",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "no",
      sortCodeSupportsDirectCredit = "no",
      sortCodeBankName = "Skipton Building Society",
      iban = ""
    ),
    BankAccount("206705", "86473611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "yes",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "yes",
      sortCodeSupportsDirectCredit = "yes",
      sortCodeBankName = "Nationwide",
      iban = ""
    ),
    BankAccount("206705", "86563611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "no",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "no",
      sortCodeSupportsDirectCredit = "yes",
      sortCodeBankName = "Nationwide",
      iban = ""
    ),
    BankAccount("206705", "76523611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "partial",
      accountName = "Acme Limited",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "Nationwide",
      iban = ""
    ),
    BankAccount("206705", "11115555") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "no",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "",
      iban = ""
    ),
    BankAccount("206705", "11116666") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "inapplicable",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "",
      iban = ""
    ),
    BankAccount("206705", "11117777") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "indeterminate",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "Barclays",
      iban = ""
    ),
    BankAccount("207106", "44311677") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "indeterminate",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "",
      iban = ""
    ),
    BankAccount("206705", "11118888") -> AccountDetails(
      accountNumberIsWellFormatted = "no",
      accountExists = "indeterminate",
      nameMatches = "",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "Barclays",
      iban = ""
    ),
    BankAccount("206704", "11119999") -> AccountDetails(
      accountNumberIsWellFormatted = "indeterminate",
      accountExists = "indeterminate",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "no",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "Barclays",
      iban = ""
    )
  )
  // pair of (sortCode, accountNumber)
  val personalBankAccountMap: Map[BankAccount, AccountDetails] = Map(
    BankAccount("206705", "44333611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "no",
      nameMatches = "no",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "no",
      sortCodeSupportsDirectCredit = "no",
      sortCodeBankName = "BARCLAYS BANK UK PLC",
      iban = "GB21BARC20670544333611"
    ),
    BankAccount("206705", "44311611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "yes",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "no",
      sortCodeSupportsDirectCredit = "no",
      sortCodeBankName = "BARCLAYS BANK UK PLC",
      iban = "GB21BARC20670544311611"
    ),
    BankAccount("609593", "91661500") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "yes",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "yes",
      sortCodeSupportsDirectCredit = "yes",
      sortCodeBankName = "Skipton Building Society",
      iban = ""
    ),
    BankAccount("206705", "44344611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "no",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "yes",
      sortCodeSupportsDirectDebit = "no",
      sortCodeSupportsDirectCredit = "yes",
      sortCodeBankName = "Nationwide",
      iban = ""
    ),
    BankAccount("206705", "44355611") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "yes",
      nameMatches = "partial",
      accountName = "John Smith",
      nonStandardAccountDetailsRequiredForBacs = "no",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "yes",
      sortCodeSupportsDirectCredit = "no",
      sortCodeBankName = "Nationwide",
      iban = ""
    ),
    BankAccount("206705", "11112222") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "no",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "",
      iban = ""
    ),
    BankAccount("206705", "11113333") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "inapplicable",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "",
      iban = ""
    ),
    BankAccount("206705", "11114444") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "indeterminate",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "Barclays",
      iban = ""
    ),
    BankAccount("207106", "44311677") -> AccountDetails(
      accountNumberIsWellFormatted = "yes",
      accountExists = "indeterminate",
      nameMatches = "indeterminate",
      accountName = "",
      nonStandardAccountDetailsRequiredForBacs = "indeterminate",
      sortCodeIsPresentOnEISCD = "",
      sortCodeSupportsDirectDebit = "",
      sortCodeSupportsDirectCredit = "",
      sortCodeBankName = "",
      iban = ""
    )
  )

  def businessBankAccountExistence = Action(parse.json[BankAccountRequest]) { request =>
    val account = request.body.account
    val accountDetailsOpt = businessBankAccountMap.get(BankAccount(account.sortCode, account.accountNumber))
    accountDetailsOpt match {
      case Some(accountDetails) =>
        Ok(Json.toJson(accountDetails))
      case None =>
        val unknownAccountDetails =
          AccountDetails(
            accountNumberIsWellFormatted = "no",
            accountExists = "no",
            nameMatches = "no",
            accountName = "",
            nonStandardAccountDetailsRequiredForBacs = "no",
            sortCodeIsPresentOnEISCD = "",
            sortCodeSupportsDirectDebit = "",
            sortCodeSupportsDirectCredit = "",
            sortCodeBankName = "",
            iban = ""
          )
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
        val unknownAccountDetails = AccountDetails(
          accountNumberIsWellFormatted = "no",
          accountExists = "no",
          nameMatches = "no",
          accountName = "",
          nonStandardAccountDetailsRequiredForBacs = "no",
          sortCodeIsPresentOnEISCD = "",
          sortCodeSupportsDirectDebit = "",
          sortCodeSupportsDirectCredit = "",
          sortCodeBankName = "",
          iban = ""
        )
        Ok(Json.toJson(unknownAccountDetails))
    }
  }

}
