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

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class EmploymentDetails @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def getEmploymentDetails(nino: String, taxYear: Int) = Action.async { request =>
    val ninoUC = nino.toUpperCase

    if (ninoUC == "AA111111A") {
      Future.successful(
        Ok("""|{
              |  "individualsEmploymentDetails": [
              |    {
              |      "payeSchemeOperatorName": "Acme",
              |      "payeSequenceNumber": 1234561,
              |      "worksNumber": "ACME01",
              |      "taxDistrictNumber": "123",
              |      "payeNumber": "AA1111",
              |      "directorIdentifier": true
              |    }
              |  ]
              |}""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA000003D") {
      Future.successful(
        Ok("""|{
              |  "individualsEmploymentDetails": [
              |    {
              |      "payeSchemeOperatorName": "Jim",
              |      "payeSequenceNumber": 1234561,
              |      "worksNumber": "ACME01",
              |      "taxDistrictNumber": "123",
              |      "payeNumber": "AA1111",
              |      "directorIdentifier": true
              |    }
              |  ]
              |}""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA555553D") {
      Future.successful(
        Ok("""|{
              |  "individualsEmploymentDetails": [
              |    {
              |      "payeSchemeOperatorName": "Lee",
              |      "payeSequenceNumber": 1234551,
              |      "worksNumber": "ACME03",
              |      "taxDistrictNumber": "013",
              |      "payeNumber": "AA5454",
              |      "directorIdentifier": true
              |    }
              |  ]
              |}""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA222222A") {
      Future.successful(
        Ok("""|{
              |  "individualsEmploymentDetails": [
              |    {
              |      "payeSchemeOperatorName": "Acme",
              |      "payeSequenceNumber": 1234561,
              |      "worksNumber": "ACME01",
              |      "taxDistrictNumber": "123",
              |      "payeNumber": "AA1111",
              |      "directorIdentifier": true
              |    },
              |    {
              |      "payeSchemeOperatorName": "Smith Holdings",
              |      "payeSequenceNumber": 2345678,
              |      "worksNumber": "SMITH01",
              |      "taxDistrictNumber": "789",
              |      "payeNumber": "BB22222",
              |      "directorIdentifier": false
              |    }
              |  ]
              |}""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA333333A") {
      Future.successful(
        Ok("""|{
              |  "individualsEmploymentDetails": [
              |    {
              |      "payeSchemeOperatorName": "Acme",
              |      "payeSequenceNumber": 1234561,
              |      "worksNumber": "ACME01",
              |      "taxDistrictNumber": "123",
              |      "payeNumber": "AA1111",
              |      "directorIdentifier": true
              |    },
              |    {
              |      "payeSchemeOperatorName": "Smith Holdings",
              |      "payeSequenceNumber": 2345678,
              |      "worksNumber": "SMITH01",
              |      "taxDistrictNumber": "789",
              |      "payeNumber": "BB22222",
              |      "directorIdentifier": false
              |    },
              |    {
              |      "payeSchemeOperatorName": "Acme",
              |      "payeSequenceNumber": 3456789,
              |      "worksNumber": "ACME09",
              |      "taxDistrictNumber": "123",
              |      "payeNumber": "AA1111",
              |      "directorIdentifier": false
              |    }
              |  ]
              |}
              |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA444444A") {
      Future.successful(
        Ok("""|{
              |  "individualsEmploymentDetails": [
              |    {
              |      "payeSchemeOperatorName": "Acme",
              |      "payeSequenceNumber": 1234561,
              |      "worksNumber": "ACME01",
              |      "taxDistrictNumber": "023",
              |      "payeNumber": "AA4444",
              |      "directorIdentifier": true
              |    },
              |    {
              |      "payeSchemeOperatorName": "Smith Holdings",
              |      "payeSequenceNumber": 2345678,
              |      "worksNumber": "SMITH01",
              |      "taxDistrictNumber": "009",
              |      "payeNumber": "BB4444",
              |      "directorIdentifier": false
              |    },
              |    {
              |      "payeSchemeOperatorName": "Acme 2",
              |      "payeSequenceNumber": 123456,
              |      "worksNumber": "ACME09",
              |      "taxDistrictNumber": "07",
              |      "payeNumber": "AA5555",
              |      "directorIdentifier": false
              |    }
              |  ]
              |}""".stripMargin).as("application/json")
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
