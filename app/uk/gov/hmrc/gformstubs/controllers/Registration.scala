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
import play.api.libs.json.{ Json, OFormat }
import uk.gov.hmrc.play.bootstrap.controller.BaseController
import uk.gov.hmrc.play.http.logging.MdcLoggingExecutionContext._
import play.api.mvc._

import scala.concurrent.Future

case class AddressDes(postalCode: String)

object AddressDes {
  implicit val format: OFormat[AddressDes] = Json.format[AddressDes]
}

@Singleton()
class Registration extends BaseController {

  def validator(utr: String) = Action.async { implicit request =>
    Logger.info(s"validator, ${request.headers.toSimpleMap.toString()}")
    if (utr.startsWith("1")) {
      Future.successful(Ok(Json.toJson(AddressDes("BN12 4XL"))))
    } else
      Future.successful(Ok(Json.toJson(AddressDes("ANYTHING"))))
  }

}