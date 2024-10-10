/*
 * Copyright 2024 HM Revenue & Customs
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

import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class CompanyDetails @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def getCompanyDetails(companyNumber: String) = Action.async { _ =>
    if (companyNumber == "11111111") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Limited",
          "company_number": "$companyNumber",
          "company_status": "active",
          "company_status_detail": "",
          "registered_office_address": {
            "address_line_1": "1 Main Street",
            "address_line_2": "Office 234",
            "care_of": "",
            "country": "UK",
            "locality": "London",
            "po_box": "",
            "postal_code": "E14 5AB",
            "premises": "",
            "region": "London"
          },
          "undeliverable_registered_office_address": false
        }
        """.stripMargin).as("application/json")
      )
    } else if (companyNumber == "22222222") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Partners",
          "company_number": "$companyNumber",
          "company_status": "active",
          "company_status_detail": "",
          "registered_office_address": {
            "address_line_1": "1 Canary Wharf",
            "address_line_2": "Building 4",
            "care_of": "",
            "country": "UK",
            "locality": "London",
            "po_box": "",
            "postal_code": "E11 1TY",
            "premises": "",
            "region": "London"
          },
          "undeliverable_registered_office_address": false
        }
        """.stripMargin).as("application/json")
      )
    } else if (companyNumber == "33333333") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Dissolved",
          "company_number": "$companyNumber",
          "company_status": "dissolved",
          "company_status_detail": "",
          "registered_office_address": {
            "address_line_1": "1 Main Street",
            "address_line_2": "Office 234",
            "care_of": "",
            "country": "UK",
            "locality": "London",
            "po_box": "",
            "postal_code": "E14 5AB",
            "premises": "",
            "region": "London"
          },
          "undeliverable_registered_office_address": false
        }
        """.stripMargin).as("application/json")
      )
    } else if (companyNumber == "44444444") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Closed",
          "company_number": "$companyNumber",
          "company_status": "closed",
          "company_status_detail": "",
          "registered_office_address": {
            "address_line_1": "1 Main Street",
            "address_line_2": "Office 234",
            "care_of": "",
            "country": "UK",
            "locality": "London",
            "po_box": "",
            "postal_code": "E14 5AB",
            "premises": "",
            "region": "London"
          },
          "undeliverable_registered_office_address": false
        }
        """.stripMargin).as("application/json")
      )
    } else if (companyNumber == "55555555") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Removed",
          "company_number": "$companyNumber",
          "company_status": "removed",
          "company_status_detail": "",
          "registered_office_address": {
            "address_line_1": "1 Main Street",
            "address_line_2": "Office 234",
            "care_of": "",
            "country": "UK",
            "locality": "London",
            "po_box": "",
            "postal_code": "E14 5AB",
            "premises": "",
            "region": "London"
          },
          "undeliverable_registered_office_address": false
        }
        """.stripMargin).as("application/json")
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

  def getCompanyOfficers(companyNumber: String) = Action.async { _ =>
    if (companyNumber == "11111111") {
      Future.successful(
        Ok("""{
             "items": [
                 {
                     "officer_role": "director"
                 },
                 {
                     "officer_role": "director"
                 },
                 {
                     "officer_role": "director"
                 },
                 {
                    "officer_role": "director"
                 },
                 {
                    "officer_role": "director"
                 },
                 {
                    "officer_role": "secretary"
                 }
             ]
          }
          """.stripMargin).as("application/json")
      )
    } else if (companyNumber == "22222222") {
      Future.successful(
        Ok("""{
             "items": [
                 {
                     "officer_role": "llp-member"
                 },
                 {
                     "officer_role": "llp-member"
                 },
                 {
                     "officer_role": "llp-member"
                 },
                 {
                     "officer_role": "llp-member"
                 },
                 {
                     "officer_role": "llp-member"
                 }
             ]
          }
          """.stripMargin).as("application/json")
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
