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
import play.api.mvc.{ AbstractController, Action, AnyContent, ControllerComponents }
import uk.gov.hmrc.domain.EmpRef
import uk.gov.hmrc.gformstubs.model.AccessResponse

import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AgentAccessControl @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  private val responseByClientId: Map[String, AccessResponse] = Map(
    "100000001" -> AccessResponse.Authorised,
    "100000002" -> AccessResponse.NoAssignment,
    "100000003" -> AccessResponse.NoRelationship
  )

  private def getDefaultResponse(agentCode: String, clientId: String): AccessResponse =
    agentCode.substring(0, 4) match {
      case "AARN" => responseByClientId.getOrElse(clientId, AccessResponse.Authorised)
      case "XARN" => AccessResponse.AgentSuspended
      case _      => AccessResponse.NoRelationship
    }

  def authorise(authType: String, agentCode: String, clientId: String): Action[AnyContent] =
    Action.async { _ =>
      val defaultResponse: AccessResponse = getDefaultResponse(agentCode, clientId)
      (authType match {
        case "epaye-auth" =>
          // This ensures correct format - throws error if not
          val empRef: EmpRef = EmpRef.fromIdentifiers(clientId)
          Future.successful {
            empRef.taxOfficeNumber match {
              case "TN000001" => AccessResponse.Authorised
              case "TN000002" => AccessResponse.NoAssignment
              case "TN000003" => AccessResponse.NoRelationship
              case _          => defaultResponse
            }
          }
        case "mtd-vat-auth" => Future.successful(defaultResponse)
        case x              => throw new IllegalArgumentException(s"Unexpected auth type: $x")
      }).map { result =>
        if (agentCode.startsWith("F"))
          Forbidden
        else {
          logger.debug(s"Agent '$agentCode' access control type '$authType' for client '$clientId' result: $result")
          result match {
            case AccessResponse.Authorised   => Ok
            case AccessResponse.NoAssignment => Unauthorized("NO_ASSIGNMENT")
            case _                           => Unauthorized("NO_RELATIONSHIP")
          }
        }
      }.recover { case e: IllegalArgumentException =>
        BadRequest(e.getMessage)
      }
    }

}
