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

import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class AgentSubscription @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def getAgentSubscription(inputArn: String) = Action.async { _ =>
    val arn = inputArn.toUpperCase

    arn match {
      case "AARN1234567" =>
        Future.successful(
          Ok("""|{
                |  "success": {
                |    "name": "Rogers Associates",
                |    "addr1": "Plaza 2",
                |    "addr2": "Ironmasters Way",
                |    "addr3": "Telford",
                |    "addr4": "Shropshire",
                |    "postcode": "TF3 4NT",
                |    "country": "GB",
                |    "phone": "01332752856",
                |    "email": "john@rogers.co.uk"
                |  }
                |}""".stripMargin).as("application/json")
        )

      case "AARN7654321" =>
        Future.successful(
          Ok("""|{
                |  "success": {
                |    "name": "Rogers USA",
                |    "addr1": "Plaza 2",
                |    "addr2": "Sunset Boulevard",
                |    "addr3": "Miami",
                |    "addr4": "Florida",
                |    "postcode": "750075",
                |    "country": "US",
                |    "phone": "01332752856",
                |    "email": "mike@rogers.co.uk"
                |  }
                |}""".stripMargin).as("application/json")
        )

      case "BARN1234567" =>
        // Minimal address (no addr3/addr4)
        Future.successful(
          Ok("""|{
                |  "success": {
                |    "name": "Rogers Associates",
                |    "addr1": "Plaza 2",
                |    "addr2": "Ironmasters Way",
                |    "postcode": "TF3 4NT",
                |    "country": "GB",
                |    "phone": "01332752856",
                |    "email": "john@rogers.co.uk"
                |  }
                |}""".stripMargin).as("application/json")
        )

      case "XARN0000401" =>
        Future.successful(
          Unauthorized("""|{
                          |  "failures": [
                          |    {
                          |      "code": "UNAUTHORIZED",
                          |      "reason": "The request is unauthorized"
                          |    }
                          |  ]
                          |}""".stripMargin).as("application/json")
        )

      case "XARN0000403" =>
        Future.successful(
          Forbidden("""|{
                       |  "failures": [
                       |    {
                       |      "code": "FORBIDDEN",
                       |      "reason": "The request is forbidden"
                       |    }
                       |  ]
                       |}""".stripMargin).as("application/json")
        )

      case "XARN0000422" =>
        Future.successful(
          UnprocessableEntity("""|{
                                 |  "failures": [
                                 |    {
                                 |      "code": "UNPROCESSABLE_ENTITY",
                                 |      "reason": "The request could not be processed"
                                 |    }
                                 |  ]
                                 |}""".stripMargin).as("application/json")
        )

      case "XARN0000500" =>
        Future.successful(
          InternalServerError(
            """|{
               |  "failures": [
               |    {
               |      "code": "SERVER_ERROR",
               |      "reason": "IF is currently experiencing problems that require live service intervention"
               |    }
               |  ]
               |}""".stripMargin
          ).as("application/json")
        )

      case "XARN0000503" =>
        Future.successful(
          ServiceUnavailable("""|{
                                |  "failures": [
                                |    {
                                |      "code": "SERVICE_UNAVAILABLE",
                                |      "reason": "Dependent systems are currently not responding"
                                |    }
                                |  ]
                                |}""".stripMargin).as("application/json")
        )

      case _ =>
        Future.successful(
          NotFound("""|{
                      |  "failures": [
                      |    {
                      |      "code": "NOT_FOUND",
                      |      "reason": "The remote endpoint has indicated that no associated data found"
                      |    }
                      |  ]
                      |}""".stripMargin).as("application/json")
        )
    }
  }
}
