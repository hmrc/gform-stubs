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

import play.api.mvc.{ AbstractController, ControllerComponents }

import org.slf4j.{ Logger, LoggerFactory }
import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class EmploymentDetails @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {


  def getEmploymentDetails(nino: String, taxYear: Int) = Action.async { implicit request =>

    val ninoLC = nino.toLowerCase

    if (ninoLC == "aa111111a") {
      Future.successful(
        Ok("""
             |{
             |  "employerName": "Acme",
             |  "sequenceNumber": 1234561,
             |  "worksNumber": "ACME01",
             |  "taxDistrictNumber": 123,
             |  "payeNumber": "AA1111",
             |  "director": "true"
             |}
          """.stripMargin).as("application/json")
      )
    } else if (ninoLC == "aa222222a") {
      Future.successful(
        Ok("""
             |[
             |{
             |  "employerName": "Acme",
             |  "sequenceNumber": 1234561,
             |  "worksNumber": "ACME01",
             |  "taxDistrictNumber": 123,
             |  "payeNumber": "AA1111",
             |  "director": "true"
             |},
             |{
             |  "employerName": "Smith Holdings",
             |  "sequenceNumber": 2345678,
             |  "worksNumber": "SMITH01",
             |  "taxDistrictNumber": 789,
             |  "payeNumber": "BB22222",
             |  "director": "false"
             |}
             |]
            """.stripMargin).as("application/json")
      )
    } else if (ninoLC == "aa333333a") {
      Future.successful(
        Ok("""
             |[
             |{
             |  "employerName": "Acme",
             |  "sequenceNumber": 1234561,
             |  "worksNumber": "ACME01",
             |  "taxDistrictNumber": 123,
             |  "payeNumber": "AA1111",
             |  "director": "true"
             |},
             |{
             |  "employerName": "Smith Holdings",
             |  "sequenceNumber": 2345678,
             |  "worksNumber": "SMITH01",
             |  "taxDistrictNumber": 789,
             |  "payeNumber": "BB22222",
             |  "director": "false"
             |},
             |{
             |  "employerName": "Acme",
             |  "sequenceNumber": 3456789,
             |  "worksNumber": "ACME09",
             |  "taxDistrictNumber": 123,
             |  "payeNumber": "AA1111",
             |  "director": "false"
             |}
             |]
            """.stripMargin).as("application/json")
      )
    } else {
      Future.successful(
        NotFound("""
                   |{
                   |   "code": "NOT_FOUND",
                   |   "reason": "The remote endpoint has indicated that no associated data found"
                   |}
           """.stripMargin).as("application/json")
      )
    }
  }
}
