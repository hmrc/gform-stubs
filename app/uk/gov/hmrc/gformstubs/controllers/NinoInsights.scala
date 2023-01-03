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
import uk.gov.hmrc.gformstubs.model.NinoInsightsRequest

import javax.inject.{ Inject, Singleton }

@Singleton
class NinoInsights @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def check =
    Action(parse.json[NinoInsightsRequest]) { request =>
      logger.info(s"Nino Insights, payload: ${request.body}")

      val nino = request.body.nino.toLowerCase

      val maybeAuthorizationToken = request.headers.headers.filter(_._1 == "Authorization").headOption

      if (maybeAuthorizationToken.isEmpty) {
        Unauthorized
      } else if (nino == "ab123456c") {
        Ok("""
             |{
             |    "riskScore": 40,
             |    "reason": "NINO_NOT_ON_WATCH_LIST"
             |}
          """.stripMargin)
      } else if (nino == "ab123456a") {
        Ok("""
             |{
             |    "riskScore": 100,
             |    "reason": "NINO_ON_WATCH_LIST"
             |}
            """.stripMargin)
      } else {
        Ok("""
             |{
             |    "riskScore": 50,
             |    "reason": "NINO_ON_WATCH_LIST"
             |}
    """.stripMargin)
      }
    }

}
