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

import org.slf4j.{ Logger, LoggerFactory }
import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class VatTrader @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def getTrader(vatRegistrationNumber: String) = Action.async { _ =>
    logger.info(s"GET VAT Trader, reg number: $vatRegistrationNumber")

    if (vatRegistrationNumber == "123456789") {
      Future.successful(
        Ok("""|{
              |  "links": {
              |    "designatoryDetails": "/vat/trader/123456789/designatory-details",
              |    "accountSummary": "/vat/trader/123456789/account-summary"
              |  },
              |  "hasDesignatoryDetails": true,
              |  "hasAccountSummary": false
              |}""".stripMargin).as("application/json")
      )
    } else if (vatRegistrationNumber == "111111111") {
      Future.successful(
        Ok("""|{
              |  "links": {
              |    "designatoryDetails": "/vat/trader/111111111/designatory-details",
              |    "accountSummary": "/vat/trader/111111111/account-summary"
              |  },
              |  "hasDesignatoryDetails": false,
              |  "hasAccountSummary": false
              |}""".stripMargin).as("application/json")
      )
    } else {
      Future.successful(
        NotFound("""
                   |{
                   |   "correlationid": "f379093d-418f-4e17-ab9f-b712b1b427e6"
                   |}
               """.stripMargin).as("application/json")
      )
    }
  }

  def getDesignatoryDetails(vatRegistrationNumber: String) = Action.async { _ =>
    logger.info(s"GET Designatory Details, reg number: $vatRegistrationNumber")

    if (vatRegistrationNumber == "123456789") {
      Future.successful(
        Ok("""|{
              |  "name": {
              |    "traderName1": "Example Trader"
              |  },
              |  "address": {
              |    "addressLine1": "123 Example Street",
              |    "addressLine2": "Example District",
              |    "addressLine3": "Example City",
              |    "addressLine4": "Example County",
              |    "addressLine5": "Example Country",
              |    "postcode": "EX4 3PL"
              |  },
              |  "contact": {
              |    "telephone": {
              |      "daytime": "01234567890",
              |      "mobile": "07123456789"
              |    },
              |    "email": {
              |      "email": "example@example.com"
              |    }
              |  }
              |}""".stripMargin).as("application/json")
      )
    } else if (vatRegistrationNumber == "111111111") {
      Future.successful(
        NotFound("""
                   |{
                   |   "correlationid": "f379093d-418f-4e17-ab9f-b712b1b427e6"
                   |}
               """.stripMargin).as("application/json")
      )
    } else {
      Future.successful(
        NotFound("""
                   |{
                   |   "correlationid": "f379093d-418f-4e17-ab9f-b712b1b427e6"
                   |}
               """.stripMargin).as("application/json")
      )
    }
  }
}
