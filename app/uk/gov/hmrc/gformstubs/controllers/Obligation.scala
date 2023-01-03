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

import javax.inject.{ Inject, Singleton }
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class Obligation @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def getTaxPeriods(idType: String, idNumber: String, regimeType: String) =
    Action.async { implicit request =>
      logger.info(s"validator, ${request.headers.toSimpleMap.toString()}")
      if (idNumber != "1234567891")
        if (List("nino", "vrn", "mtdbis", "ni", "utr", "safeId", "eeits") contains idType)
          if (
            List(
              "VATC",
              "ITSA",
              "NI",
              "PARC",
              "AGL",
              "LFT",
              "APD",
              "IPT",
              "BF",
              "GR",
              "BD",
              "GD",
              "LD",
              "BFP",
              "GRF",
              "FD",
              "VTR",
              "AIR"
            ) contains regimeType
          )
            Future.successful(
              Ok("""
                   |{
                   |    "obligations": [
                   |        {
                   |            "obligationDetails": [
                   |                {
                   |                    "status": "O",
                   |                    "inboundCorrespondenceFromDate": "2022-04-01",
                   |                    "inboundCorrespondenceToDate": "2022-04-30",
                   |                    "inboundCorrespondenceDueDate": "2022-05-15",
                   |                    "periodKey": "17B1"
                   |                },
                   |                {
                   |                    "status": "O",
                   |                    "inboundCorrespondenceFromDate": "2022-03-01",
                   |                    "inboundCorrespondenceToDate": "2022-03-31",
                   |                    "inboundCorrespondenceDueDate": "2022-04-15",
                   |                    "periodKey": "17B2"
                   |                },
                   |                {
                   |                    "status": "O",
                   |                    "inboundCorrespondenceFromDate": "2021-03-01",
                   |                    "inboundCorrespondenceToDate": "2021-03-31",
                   |                    "inboundCorrespondenceDueDate": "2021-04-15",
                   |                    "periodKey": "17B3"
                   |                },
                   |                {
                   |                    "status": "O",
                   |                    "inboundCorrespondenceFromDate": "2019-12-01",
                   |                    "inboundCorrespondenceToDate": "2019-12-31",
                   |                    "inboundCorrespondenceDueDate": "2020-01-15",
                   |                    "periodKey": "17B4"
                   |                },
                   |                {
                   |                    "status": "O",
                   |                    "inboundCorrespondenceFromDate": "2018-04-01",
                   |                    "inboundCorrespondenceToDate": "2019-03-31",
                   |                    "inboundCorrespondenceDueDate": "2019-04-15",
                   |                    "periodKey": "17B5"
                   |                }
                   |            ]
                   |        }
                   |    ]
                   |}
            """.stripMargin)
                .as("application/json")
            )
          else
            Future.successful(BadRequest("regimeType invalid"))
        else
          Future.successful(BadRequest("idType invalid"))
      else
        Future.successful(
          NotFound("""
                     |{
                     |   "code": "NOT_FOUND",
                     |   "reason": "The remote endpoint has indicated that no associated data found"
                     |}
           """.stripMargin)
            .as("application/json")
        )
    }
}
