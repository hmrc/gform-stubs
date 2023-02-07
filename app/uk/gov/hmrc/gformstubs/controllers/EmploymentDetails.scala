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

import org.slf4j.{ Logger, LoggerFactory }
import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }


@Singleton
class EmploymentDetails @Inject() (controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def getEmploymentDetails(nino: String, taxYear: Int) =
    Action(parse.json[NinoInsightsRequest]) { request =>
      logger.info(s"Nino Insights, payload: ${request.body}")

      val ninoLC = request.body.nino.toLowerCase

      if (nino == "aa111111a") {
        Ok(
          """
            |{
            |  "employerName": "Acme"
            |  "sequenceNumber": 1234561
            |  "worksNumber": "ACME01"
            |  "taxDistrictNumber": 123
            |  "payeNumber": "AA1111"
            |  "director": "true"
            |}
          """.stripMargin)
      } else if (nino == "aa222222a") {
        Ok(
          """
            |[
            |{
            |  "employerName": "Acme"
            |  "sequenceNumber": 1234561
            |  "worksNumber": "ACME01"
            |  "taxDistrictNumber": 123
            |  "payeNumber": "AA1111"
            |  "director": "true"
            |},
            |{
            |  "employerName": "Smith Holdings"
            |  "sequenceNumber": 2345678
            |  "worksNumber": "SMITH01"
            |  "taxDistrictNumber": 789
            |  "payeNumber": "BB22222"
            |  "director": "false"
            |}
            |]
          """.stripMargin)
      } else if (nino == "aa333333a") {
        Ok(
          """
            |[
            |{
            |  "employerName": "Acme"
            |  "sequenceNumber": 1234561
            |  "worksNumber": "ACME01"
            |  "taxDistrictNumber": 123
            |  "payeNumber": "AA1111"
            |  "director": "true"
            |},
            |{
            |  "employerName": "Smith Holdings"
            |  "sequenceNumber": 2345678
            |  "worksNumber": "SMITH01"
            |  "taxDistrictNumber": 789
            |  "payeNumber": "BB22222"
            |  "director": "false"
            |},
            |{
            |  "employerName": "Acme"
            |  "sequenceNumber": 3456789
            |  "worksNumber": "ACME09"
            |  "taxDistrictNumber": 123
            |  "payeNumber": "AA1111"
            |  "director": "false"
            |}
            |]
          """.stripMargin)
      } else if (nino == "aa444444a") {
        NotFound(
          """
            |{
            |   "code": "NOT_FOUND",
            |   "reason": "The remote endpoint has indicated that no associated data found"
            |}
         """.stripMargin)
      }

    }
}