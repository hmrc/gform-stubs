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
import uk.gov.hmrc.gformstubs.model.BankAccountInsightsRequest

import javax.inject.{ Inject, Singleton }

@Singleton
class BankAccountInsights @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def check =
    Action(parse.json[BankAccountInsightsRequest]) { request =>
      logger.info(s"BankAccount Insights, payload: ${request.body}")

      val sortCode = request.body.sortCode.toLowerCase
      val accountNumber = request.body.accountNumber.toLowerCase

      if (sortCode == "111111" && accountNumber == "12345678") {
        Ok("""
             |{
             |    "riskScore": 40,
             |    "reason": "ACCOUNT_NOT_ON_WATCH_LIST"
             |}
          """.stripMargin)
      } else if (sortCode == "222222" && accountNumber == "12345678") {
        Ok("""
             |{
             |    "riskScore": 100,
             |    "reason": "ACCOUNT_ON_WATCH_LIST"
             |}
            """.stripMargin)
      } else {
        Ok("""
             |{
             |    "riskScore": 50,
             |    "reason": "ACCOUNT_ON_WATCH_LIST"
             |}
    """.stripMargin)
      }
    }

}
