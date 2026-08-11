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

import play.api.libs.json.Json
import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class Pega @Inject() (controllerComponents: ControllerComponents) extends AbstractController(controllerComponents) {

  private object Headers {
    val Originator = "x-originator"
    val TargetApplication = "x-target-application"
    val CorrelationId = "correlationid"
  }

  def createCase() = Action.async(parse.json) { request =>
    val maybeCorrelationId = request.headers.get(Headers.CorrelationId)
    val maybeCaseTypeId = (request.body \ "caseTypeId").asOpt[String]

    val response =
      maybeCaseTypeId.getOrElse("") match {
        case "400" | "bad-request" =>
          BadRequest(
            Json.obj(
              "origin" -> "HIP",
              "response" -> Json.arr(
                Json.obj(
                  "type"   -> "Type of 400 Failure",
                  "reason" -> "Reason for 400 Failure"
                )
              )
            )
          )
        case "422" | "unprocessable-entity" =>
          UnprocessableEntity(
            Json.obj(
              "errors" -> Json.arr(
                Json.obj(
                  "ID"      -> "INVALID_CASE_TYPE",
                  "message" -> "caseTypeId is invalid"
                )
              ),
              "httpResponseCode" -> 422
            )
          )
        case "500" | "internal-server-error" =>
          InternalServerError(
            Json.obj(
              "origin" -> "HIP",
              "response" -> Json.arr(
                Json.obj(
                  "type"   -> "Type of 500 Failure",
                  "reason" -> "Reason for 500 Failure"
                )
              )
            )
          ).as("application/json;charset=UTF-8")
        case "503" | "service-unavailable" =>
          ServiceUnavailable(
            Json.obj(
              "origin" -> "HIP",
              "response" -> Json.arr(
                Json.obj(
                  "type"   -> "Type of 503 Failure",
                  "reason" -> "Reason for 503 Failure"
                )
              )
            )
          ).as("application/json;charset=UTF-8")
        case _ =>
          Accepted(
            Json.obj(
              "httpResponseCode" -> 202,
              "serviceCaseId"    -> "HMRC-EXPENSE-WORK EXP-177019"
            )
          )
      }

    Future.successful(
      maybeCorrelationId match {
        case Some(correlationId) => response.withHeaders(Headers.CorrelationId -> correlationId)
        case None                => response
      }
    )
  }

  def getCase(caseId: String, actionId: String) = Action.async { _ =>
    if (caseId.toLowerCase == "hmrc-expense-work exp-177019") {
      Future.successful(
        Ok("""|  {
              |    "data": {
              |       "caseInfo" : {}
              |    }
              |  }""".stripMargin)
          .as("application/json")
          .withHeaders("etag" -> "20250728T122544.220 GMT")
      )
    } else if (caseId.toLowerCase == "hmrc-expense-work exp-176012") {
      Future.successful(
        Ok("""|  {
              |    "data": {
              |       "caseInfo" : {}
              |    }
              |  }""".stripMargin)
          .as("application/json")
          .withHeaders("etag" -> "20250729T122544.220 GMT")
      )
    } else if (caseId.toLowerCase == "hmrc-expense-work exp-500500") {
      Future.successful(
        InternalServerError("""|  {
                               |  "origin": "HIP",
                               |  "response": [
                               |    {
                               |      "type": "Type of 500 Failure",
                               |      "reason": "Reason for 500 Failure"
                               |    }
                               |  ]
                               |}""".stripMargin)
          .as("application/json")
      )
    } else {
      Future.successful(
        NotFound(s"""
                    |{
                    |  "errorClassification": "$caseId/$actionId not found",
                    |  "localizedValue": "$caseId/$actionId not found"
                    |}
           """.stripMargin).as("application/json")
      )
    }
  }

  def updateCase(caseId: String) = Action.async { request =>
    if (caseId.nonEmpty) {
      request.headers.get("if-match") match {
        case Some(etag) =>
          Future.successful(
            Ok(s"""|  {
                   |    "data": {
                   |       "caseInfo" : {
                   |         "id": "$caseId"
                   |       }
                   |    }
                   |  }""".stripMargin)
              .as("application/json")
              .withHeaders("etag" -> s"$etag")
          )
        case None =>
          Future.successful(
            BadRequest(s"""|  {
                           |  "origin": "HIP",
                           |  "response": [
                           |    {
                           |      "type": "etag failure",
                           |      "reason": "etag missing"
                           |    }
                           |  ]
                           |}""".stripMargin)
              .as("application/json")
          )
      }
    } else {
      Future.successful(
        BadRequest(s"""|  {
                       |  "origin": "HIP",
                       |  "response": [
                       |    {
                       |      "type": "caseId failure",
                       |      "reason": "caseId missing"
                       |    }
                       |  ]
                       |}""".stripMargin)
          .as("application/json")
      )
    }
  }
}
