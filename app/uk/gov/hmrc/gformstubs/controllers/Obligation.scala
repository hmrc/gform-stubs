/*
 * Copyright 2019 HM Revenue & Customs
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

import javax.inject.Singleton
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import uk.gov.hmrc.play.bootstrap.controller.BaseController

import scala.concurrent.Future

@Singleton()
class Obligation extends BaseController {

  def getTaxPeriods(idType: String, idNumber: String, regimeType: String) = Action.async { implicit request =>
    Logger.info(s"validator, ${request.headers.toSimpleMap.toString()}")
    if (idType == "nino") {
      Future.successful(
        Ok("""
             |{
             |"obligations" :
             |{
             |"taxPeriods" :
             |[
             |{
             |"inboundCorrespondenceFromDate" : "2019-05-23",
             |"inboundCorrespondenceToDate" : "b",
             |"periodKey" : "c"
             |},
             |{
             |"inboundCorrespondenceFromDate" : "2019-06-24",
             |"inboundCorrespondenceToDate" : "2019-09-24",
             |"periodKey" : "#001"
             |}
             |]
             |}
             |}""".stripMargin)
          .as("application/json"))
    } else {
      Future.successful(BadRequest("idType wasn't nino"))
    }
  }

}
