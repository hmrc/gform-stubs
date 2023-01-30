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
import uk.gov.hmrc.gformstubs.model.SdesNotifyRequest

import javax.inject.{ Inject, Singleton }

@Singleton
class SdesNotification @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def notifySDES = Action(parse.json[SdesNotifyRequest]) { request =>
    logger.info(s"SDES Notification: ${request.body}")
    Ok
  }
}