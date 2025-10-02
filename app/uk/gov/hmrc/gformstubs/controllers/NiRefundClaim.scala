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

import play.api.mvc.{ AbstractController, ControllerComponents }
import uk.gov.hmrc.gformstubs.model.NiRefundBankAccountRequest

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class NiRefundClaim @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def validateNiClaimReference(nino: String, refundClaimReference: String) = Action.async { _ =>
    if (refundClaimReference == "11111111") {
      Future.successful(
        Ok("""|{
              |  "RefundDetails": {
              |    "refundType": "Excess",
              |    "taxYear": "2025-04-06",
              |    "class2ContributionWeeks": 0,
              |    "class3ContributionWeeks": 0
              |  }
              |}""".stripMargin)
          .as("application/json")
      )
    } else if (refundClaimReference == "22222222") {
      Future.successful(
        Ok("""|{
              |  "RefundDetails": {
              |    "refundType": "Excess",
              |    "taxYear": "2025-04-06",
              |    "class2ContributionWeeks": 0,
              |    "class3ContributionWeeks": 0,
              |    "weeksOfCredits": 10
              |  }
              |}""".stripMargin)
          .as("application/json")
      )
    } else if (refundClaimReference == "33333333") {
      Future.successful(
        Ok("""|{
              |  "RefundDetails": {
              |    "refundType": "Excess",
              |    "taxYear": "2025-04-06",
              |    "class2ContributionWeeks": 10,
              |    "class3ContributionWeeks": 20,
              |    "weeksOfCredits": 30
              |  }
              |}""".stripMargin)
          .as("application/json")
      )
    } else if (refundClaimReference == "44444444") {
      Future.successful(
        Ok("""|{
              |  "RefundDetails": {
              |    "refundType": "Excess",
              |    "taxYear": "2025-04-06",
              |    "class2ContributionWeeks": 11,
              |    "class3ContributionWeeks": 21
              |  }
              |}""".stripMargin)
          .as("application/json")
      )
    } else if (refundClaimReference == "55555555") {
      Future.successful(
        Ok("""|{
              |  "RefundDetails": {
              |    "refundType": "Erroneous",
              |    "taxYear": "2025-04-06",
              |    "class2ContributionWeeks": 12,
              |    "class3ContributionWeeks": 22
              |  }
              |}""".stripMargin)
          .as("application/json")
      )
    } else {
      Future.successful(
        NotFound(s"Resource not found for Nino: $nino and Claim Reference: $refundClaimReference")
      )
    }
  }

  def updateBankDetails(nino: String, refundClaimReference: String) =
    Action.async(parse.json[NiRefundBankAccountRequest]) { request =>
      if (!validateBankDetails(request.body)) {
        Future.successful(
          NotFound("Bank account details not found")
        )
      } else {
        if (refundClaimReference == "11111111" && validateBankDetails(request.body)) {
          Future.successful(
            Ok(s"""|{
                   |  "personOptimisticLockResponse": {
                   |    "nationalInsuranceNumber": "$nino",
                   |    "currentOptimisticLock": 86,
                   |    "updatedOptimisticLock": 87
                   |  }
                   |}""".stripMargin)
              .as("application/json")
          )
        } else if (refundClaimReference == "22222222") {
          Future.successful(
            Ok(s"""|{
                   |  "personOptimisticLockResponse": {
                   |    "nationalInsuranceNumber": "$nino",
                   |    "currentOptimisticLock": 88,
                   |    "updatedOptimisticLock": 89
                   |  }
                   |}""".stripMargin)
              .as("application/json")
          )
        } else if (refundClaimReference == "33333333") {
          Future.successful(
            Ok(s"""|{
                   |  "personOptimisticLockResponse": {
                   |    "nationalInsuranceNumber": "$nino",
                   |    "currentOptimisticLock": 90,
                   |    "updatedOptimisticLock": 91
                   |  }
                   |}""".stripMargin)
              .as("application/json")
          )
        } else if (refundClaimReference == "44444444") {
          Future.successful(
            Ok(s"""|{
                   |  "personOptimisticLockResponse": {
                   |    "nationalInsuranceNumber": "$nino",
                   |    "currentOptimisticLock": 92,
                   |    "updatedOptimisticLock": 93
                   |  }
                   |}""".stripMargin)
              .as("application/json")
          )
        } else if (refundClaimReference == "55555555") {
          Future.successful(
            Ok(s"""|{
                   |  "personOptimisticLockResponse": {
                   |    "nationalInsuranceNumber": "$nino",
                   |    "currentOptimisticLock": 94,
                   |    "updatedOptimisticLock": 95
                   |  }
                   |}""".stripMargin)
              .as("application/json")
          )
        } else {
          Future.successful(
            NotFound(s"Resource not found for Nino: $nino and Claim Reference: $refundClaimReference")
          )
        }
      }
    }

  private def validateBankDetails(bankAccount: NiRefundBankAccountRequest): Boolean =
    bankAccount.refundClaimBankDetails.accountNumber.nonEmpty && bankAccount.refundClaimBankDetails.sortCode.nonEmpty
}
