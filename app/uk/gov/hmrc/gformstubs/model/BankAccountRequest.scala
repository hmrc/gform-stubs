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

package uk.gov.hmrc.gformstubs.model

import play.api.libs.json.{ Json, OFormat, OWrites, Reads, __ }

case class BankAccountRequest(account: BankAccount)

object BankAccountRequest {
  implicit val format: OFormat[BankAccountRequest] = Json.format[BankAccountRequest]
}

case class NiRefundBankAccountRequest(refundClaimBankDetails: BankAccount)

object NiRefundBankAccountRequest {
  private val upperCaseKey: String = "RefundClaimBankDetails"

  implicit val reads: Reads[NiRefundBankAccountRequest] =
    (__ \ upperCaseKey).read[BankAccount].map(NiRefundBankAccountRequest.apply)

  implicit val writes: OWrites[NiRefundBankAccountRequest] = OWrites { req =>
    Json.obj(upperCaseKey -> Json.toJson(req.refundClaimBankDetails))
  }

  implicit val format: OFormat[NiRefundBankAccountRequest] = OFormat(reads, writes)
}

case class BankAccount(sortCode: String, accountNumber: String)

object BankAccount {
  implicit val format: OFormat[BankAccount] = Json.format[BankAccount]
}
