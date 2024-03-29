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
import play.api.libs.json._
import play.api.mvc._
import uk.gov.hmrc.gformstubs.model.DesRegistrationRequest
import uk.gov.hmrc.gformstubs.model._
import uk.gov.hmrc.gformstubs.generators.DesRegistrationResponseGen

import scala.concurrent.Future

@Singleton
class Registration @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def validator(utr: String) =
    Action(parse.json[DesRegistrationRequest]) { request =>
      logger.info(s"Registration, utr: $utr, payload: ${request.body}")

      utr match {
        case "random" =>
          val random = DesRegistrationResponseGen.desRegistrationResponseGen
          Ok(Json.toJson(addPostCode(random, Some("random"))))
        case "nopostcode" =>
          val random = DesRegistrationResponseGen.desRegistrationResponseGen
          Ok(Json.toJson(addPostCode(random, None)))
        case a if a.startsWith("ind") =>
          Ok(Json.toJson(Registration.desIndividual))
        case a if a.startsWith("org") =>
          Ok(Json.toJson(Registration.desOrganisation))
        case "unauthorized" => Unauthorized
        case "fail" =>
          BadRequest(
            Json.toJson(
              DesRegistrationResponseError("INVALID_PAYLOAD", "Submission has not passed validation. Invalid payload.")
            )
          )
        case otherwise if otherwise.size == 10 =>
          BadRequest(
            Json.toJson(
              DesRegistrationResponseError("NOT_FOUND", "The remote endpoint has indicated that no data can be found")
            )
          )
        case otherwise =>
          BadRequest(
            Json.toJson(
              DesRegistrationResponseError(
                "INVALID_UTR",
                "Submission has not passed validation. Invalid parameter UTR."
              )
            )
          )

      }
    }

  private def addPostCode(drr: DesRegistrationResponse, postcode: Option[String]): DesRegistrationResponse =
    drr.copy(address = drr.address match {
      case s: UkAddress            => s.copy(postalCode = postcode)
      case s: InternationalAddress => s.copy(postalCode = postcode)
    })

  def getAgentRecord(inputArn: String) = Action.async { _ =>
    val arn = inputArn.toUpperCase

    if (arn == "AARN1234567") {
      Future.successful(
        Ok("""|{
              |  "contactDetails":{
              |    "phoneNumber":"01332752856"
              |  },
              |  "agencyDetails":{
              |    "agencyName":"Rogers associates",
              |    "agencyAddress":{
              |      "addressLine1":"Plaza 2",
              |      "addressLine2":"Ironmasters Way",
              |      "addressLine3":"Telford",
              |      "addressLine4":"Shropshire",
              |      "postalCode":"TF3 4NT",
              |      "countryCode":"GB"
              |    },
              |    "agencyEmail":"john@rogers.co.uk"
              |  }
              |}""".stripMargin).as("application/json")
      )
    } else if (arn == "BARN1234567") {
      Future.successful(
        Ok("""|{
              |  "contactDetails":{
              |    "phoneNumber":"01332752856"
              |  },
              |  "agencyDetails":{
              |    "agencyName":"Rogers associates",
              |    "agencyAddress":{
              |      "addressLine1":"Plaza 2",
              |      "addressLine2":"Telford",
              |      "postalCode":"TF3 4NT",
                      "countryCode":"GB"
              |    },
              |    "agencyEmail":"john@rogers.co.uk"
              |  }
              |}""".stripMargin).as("application/json")
      )
    } else if (arn == "AARN7654321") {
      Future.successful(
        Ok("""|{
              |  "contactDetails":{
              |    "phoneNumber":"01332752856"
              |  },
              |  "agencyDetails":{
              |    "agencyName":"Rogers USA",
              |    "agencyAddress":{
              |      "addressLine1":"Plaza 2",
              |      "addressLine2":"Sunset Boulevard",
              |      "addressLine3":"Miami",
              |      "addressLine4":"Florida",
              |      "postalCode":"750075",
              |      "countryCode":"US"
              |    },
              |    "agencyEmail":"mike@rogers.co.uk"
              |  }
              |}""".stripMargin).as("application/json")
      )
    } else if (arn == "BARN7654321") {
      Future.successful(
        Ok("""|{
              |  "contactDetails":{
              |    "phoneNumber":"01332752856"
              |  },
              |  "agencyDetails":{
              |    "agencyName":"Rogers USA",
              |    "agencyAddress":{
              |      "addressLine1":"Plaza 2",
              |      "addressLine2":"Miami",
              |      "countryCode":"US"
              |    },
              |    "agencyEmail":"mike@rogers.co.uk"
              |  }
              |}""".stripMargin).as("application/json")
      )
    } else {
      Future.successful(
        NotFound("""
                   |{
                   |   "code": "NOT_FOUND",
                   |   "reason": "The remote endpoint has indicated that no associated data found"
                   |}
           """.stripMargin).as("application/json")
      )
    }
  }
}

object Registration {
  val desOrganisation = {
    val organisation = Organisation("CLICKEYCLOUSE", false, "Not Specified")
    val address =
      UkAddress("75, LOWER STREET,", Some("FULHAM-UNDER-WATER"), Some("MUCH PETRIFYING"), None, Some("BN12 4XL"))
    val contactDetails = ContactDetails(Some("0123 456789"), None, None, None)

    DesRegistrationResponse(
      "XE0000100007638",
      Some("AARN1234567"),
      "0100007638",
      false,
      false,
      false,
      false,
      organisation,
      address,
      Some(contactDetails)
    )
  }

  val desIndividual = {
    val individual = Individual("DAVID", Some("BOWEN"), None)
    val address =
      InternationalAddress("1 OnLoan Parade", Some("Nowhere"), Some("England"), Some("UK"), "IT", Some("BN12 4XL"))
    val contactDetails = ContactDetails(Some("199 206 3434"), Some("01474252553"), None, None)
    DesRegistrationResponse(
      "XR0000100028912",
      None,
      "0100028912",
      false,
      false,
      false,
      true,
      individual,
      address,
      Some(contactDetails)
    )
  }
}
