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

import play.api.libs.json.{ Json, OFormat }
import play.api.mvc.{ AbstractController, ControllerComponents }
import uk.gov.hmrc.gformstubs.model.IndiaFTARegistration

import java.time.temporal.ChronoUnit
import java.time.{ Instant, ZoneOffset, ZonedDateTime }
import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class ContainerManagedArchitecture @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def postVatParcelsC2C() = Action.async { _ =>
    val instant: String =
      ZonedDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS).toString

    Future.successful(
      Ok(
        s"""
           |{
           |  "success": {
           |    "processingDate": "$instant"
           |  }
           |}
           |""".stripMargin
      ).as("application/json")
    )
  }

  def indiaFTARegister() = Action(parse.json[IndiaFTARegistration]) { request =>
    val businessName = request.body.BusinessName.toLowerCase()
    val correlationId = request.headers.get("X-Correlation-Id")
    val instant: String =
      ZonedDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS).toString

    if (businessName.contains("error"))
      InternalServerError(
        s"""|{
            |  "errorDetail": {
            |    "errorCode": "500",
            |    "errorMessage": "Unknown error occurred",
            |    "source": "journey-<journey-name>-service-camel",
            |    "sourceFaultDetail": {},
            |    "timestamp": "$instant",
            |    "correlationId": "${correlationId.getOrElse("unknown")}"
            |  }
            |}""".stripMargin
      ).as("application/json")
    else
      Ok(
        s"""|{
            |  "pyStatusValue": "Success"
            |}
            |""".stripMargin
      ).as("application/json")
  }

}
