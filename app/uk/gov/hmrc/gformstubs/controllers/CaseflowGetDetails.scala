/*
 * Copyright 2026 HM Revenue & Customs
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

import play.api.mvc.{ AbstractController, Action, AnyContent, ControllerComponents }

import javax.inject.Inject

class CaseflowGetDetails @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  //https://admin.tax.service.gov.uk/integration-hub/apis/details/61cffebd-1f98-4424-b048-f974e77a64f1
  def getCaseDetails(caseId: String): Action[AnyContent] = Action { _ =>
    val response = caseId.toUpperCase match {
      case "CFS-10404"  => Ok("""{
                               |  "pyHTTPResponseCode": 1
                               |}""".stripMargin)
      case "CFS-104042" => Ok("""{
                                |  "pyHTTPResponseCode": 1
                                |}""".stripMargin)
      case "CFS-104043" => Ok("""{
                                |  "pyHTTPResponseCode": 1
                                |}""".stripMargin)
      case "CFS-10200"  => Ok("""{
                               |  "caseStatus": "Open",
                               |  "oudnStatus": "oudn Exists",
                               |  "resolvedTimestamp": "20260117T201550.325 GMT"
                               |}""".stripMargin)
      case "CFS-10999"  => Ok("""{
                               |  "caseStatus": "Unknown",
                               |  "oudnStatus": "oudn Exists",
                               |  "resolvedTimestamp": "20260117T201550.325 GMT"
                               |}""".stripMargin)
      case "CFS-10400"  => BadRequest("""{
                                       |  "origin": "HIP",
                                       |  "response": [
                                       |    {
                                       |      "type": "Type of Failure",
                                       |      "reason": "Reason for Failure"
                                       |    }
                                       |  ]
                                       |}""".stripMargin)
      case "CFS-10500"  => InternalServerError("""{
                                                |  "origin": "HIP",
                                                |  "response": [
                                                |    {
                                                |      "type": "Type of Failure",
                                                |      "reason": "Reason for Failure"
                                                |    }
                                                |  ]
                                                |}""".stripMargin)
      case "CFS-10503"  => ServiceUnavailable("""{
                                               |  "origin": "HIP",
                                               |  "response": [
                                               |    {
                                               |      "type": "Type of Failure",
                                               |      "reason": "Reason for Failure"
                                               |    }
                                               |  ]
                                               |}""".stripMargin)
      case _            => Ok("""{
                     |  "caseStatus": "Open",
                     |  "oudnStatus": "oudn Exists",
                     |  "resolvedTimestamp": "20260117T201550.325 GMT"
                     |}""".stripMargin)
    }

    response.as("application/json")
  }
}
