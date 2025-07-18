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

import java.time.format.DateTimeFormatter
import java.time.{ ZoneId, ZonedDateTime }
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
    } else if (companyNumber == "66666666") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Insolvency 1",
          "company_number": "$companyNumber",
          "company_status": "insolvency-proceedings",
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
    } else if (companyNumber == "77777777") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Insolvency 2",
          "company_number": "$companyNumber",
          "company_status": "insolvency-proceedings",
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
    } else if (companyNumber == "88888888") {
      Future.successful(
        Ok(s"""{
          "can_file": false,
          "company_name": "Acme Insolvency No Practitioners",
          "company_number": "$companyNumber",
          "company_status": "insolvency-proceedings",
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
        Ok(s"""
              |{
              |  "active_count": 6,
              |  "etag": "biglongstringofchars",
              |  "items": [
              |    {
              |      "address": {
              |        "address_line_1": "1 Somewhere Place",
              |        "country": "England",
              |        "locality": "Townsville",
              |        "postal_code": "SW1 2WK",
              |        "premises": "Some premises"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Jim Morrison",
              |      "officer_role": "director",
              |      "person_number": "123456789012"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "Eccles House",
              |        "address_line_2": "123 Some Road",
              |        "country": "England",
              |        "locality": "Manchester",
              |        "postal_code": "SW3 1WK"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Elton John",
              |      "officer_role": "director",
              |      "person_number": "123456789013"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "15 Actual Street",
              |        "country": "Scotland",
              |        "locality": "Pitlochry",
              |        "postal_code": "SC1 2XX"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Billy Connolly",
              |      "officer_role": "director",
              |      "person_number": "123456789013"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "132 Some Avenue",
              |        "country": "United Kingdom",
              |        "locality": "Somewheresville",
              |        "postal_code": "SW5 2WK"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Katherine Middleton",
              |      "officer_role": "director",
              |      "person_number": "123456789014"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "15 Nowhere Road",
              |        "country": "United Kingdom",
              |        "locality": "Nowhere",
              |        "postal_code": "NW1 2WE"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Kylie Minogue",
              |      "officer_role": "director",
              |      "person_number": "123456789015"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "1 Somewhere Plaza",
              |        "country": "United Kingdom",
              |        "locality": "Upper Tooting",
              |        "postal_code": "SW1 2WK"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Benedict Cumberbatch",
              |      "officer_role": "secretary",
              |      "person_number": "123456789012"
              |    }
              |  ],
              |  "items_per_page": 35,
              |  "kind": "officer-list",
              |  "links": {
              |    "self": "/company/$companyNumber/officers"
              |  },
              |  "resigned_count": 0,
              |  "inactive_count": 0,
              |  "start_index": 0,
              |  "total_results": 6
              |}
              |""".stripMargin).as("application/json")
      )
    } else if (companyNumber == "22222222") {
      Future.successful(
        Ok(s"""
              |{
              |  "active_count": 5,
              |  "etag": "biglongstringofchars",
              |  "items": [
              |    {
              |      "address": {
              |        "address_line_1": "1 Somewhere Place",
              |        "country": "England",
              |        "locality": "Townsville",
              |        "postal_code": "SW1 2WK",
              |        "premises": "Some premises"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Jim Morrison",
              |      "officer_role": "llp-member",
              |      "person_number": "123456789012"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "Eccles House",
              |        "address_line_2": "123 Some Road",
              |        "country": "England",
              |        "locality": "Manchester",
              |        "postal_code": "SW3 1WK"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Elton John",
              |      "officer_role": "llp-member",
              |      "person_number": "123456789013"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "15 Actual Street",
              |        "country": "Scotland",
              |        "locality": "Pitlochry",
              |        "postal_code": "SC1 2XX"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Billy Connolly",
              |      "officer_role": "llp-member",
              |      "person_number": "123456789013"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "132 Some Avenue",
              |        "country": "United Kingdom",
              |        "locality": "Somewheresville",
              |        "postal_code": "SW5 2WK"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Katherine Middleton",
              |      "officer_role": "llp-member",
              |      "person_number": "123456789014"
              |    },
              |    {
              |      "address": {
              |        "address_line_1": "15 Nowhere Road",
              |        "country": "United Kingdom",
              |        "locality": "Nowhere",
              |        "postal_code": "NW1 2WE"
              |      },
              |      "appointed_on": "2016-09-13",
              |      "is_pre_1992_appointment": false,
              |      "links": {
              |        "self": "/company/$companyNumber/appointments/somechars",
              |        "officer": {
              |          "appointments": "/officers/somechars/appointments"
              |        }
              |      },
              |      "name": "Kylie Minogue",
              |      "officer_role": "llp-member",
              |      "person_number": "123456789015"
              |    }
              |  ],
              |  "items_per_page": 35,
              |  "kind": "officer-list",
              |  "links": {
              |    "self": "/company/$companyNumber/officers"
              |  },
              |  "resigned_count": 0,
              |  "inactive_count": 0,
              |  "start_index": 0,
              |  "total_results": 5
              |}
              |
              |""".stripMargin).as("application/json")
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

  def getCompanyInsolvency(companyNumber: String) = Action.async { _ =>
    if (companyNumber == "66666666") {
      Future.successful(
        Ok(s"""
              |{
              |  "etag": "somelongstring",
              |  "cases": [
              |    {
              |      "type": "receiver-manager",
              |      "dates": [],
              |      "practitioners": [
              |        {
              |          "name": "John Smith",
              |          "address": {
              |            "address_line_1": "1 Somewhere St",
              |            "address_line_2": "Someburb",
              |            "locality": "Somewheresville",
              |            "postal_code": "SM1 2WH"
              |          },
              |          "appointed_on": "2022-08-05",
              |          "role": "receiver-manager"
              |        },
              |        {
              |          "name": "Jane Doe",
              |          "address": {
              |            "address_line_1": "14 Some Place",
              |            "address_line_2": "Differentburb",
              |            "locality": "Nowhere",
              |            "postal_code": "NW8 6WK"
              |          },
              |          "appointed_on": "2022-08-05",
              |          "role": "receiver-manager"
              |        }
              |      ],
              |      "links": {
              |        "charge": "/company/$companyNumber/charges/somestring"
              |      },
              |      "number": "1"
              |    },
              |    {
              |      "type": "receiver-manager",
              |      "dates": [],
              |      "practitioners": [
              |        {
              |          "name": "John Smith",
              |          "address": {
              |            "address_line_1": "1 Somewhere St",
              |            "address_line_2": "Someburb",
              |            "locality": "Somewheresville",
              |            "postal_code": "SM1 2WH"
              |          },
              |          "appointed_on": "2022-08-05",
              |          "role": "receiver-manager"
              |        },
              |        {
              |          "name": "Jane Doe",
              |          "address": {
              |            "address_line_1": "14 Some Place",
              |            "address_line_2": "Differentburb",
              |            "locality": "Nowhere",
              |            "postal_code": "NW8 6WK"
              |          },
              |          "appointed_on": "2022-08-05",
              |          "ceased_to_act_on": "2023-09-21",
              |          "role": "receiver-manager"
              |        }
              |      ],
              |      "links": {
              |        "charge": "/company/$companyNumber/charges/somestring"
              |      },
              |      "number": "2"
              |    },
              |    {
              |      "type": "in-administration",
              |      "dates": [
              |        {
              |          "type": "administration-started-on",
              |          "date": "2022-10-21"
              |        }
              |      ],
              |      "practitioners": [
              |        {
              |          "name": "John McSmith",
              |          "address": {
              |            "address_line_1": "58 Another Rd",
              |            "locality": "Anothertown",
              |            "region": "Anothershire",
              |            "postal_code": "AN3 1WE"
              |          },
              |          "role": "practitioner"
              |        }
              |      ],
              |      "number": "3"
              |    }
              |  ],
              |  "status": [
              |    "in-administration",
              |    "receiver-manager"
              |  ]
              |}
              |""".stripMargin).as("application/json")
      )
    } else if (companyNumber == "77777777") {
      val nowDate = ZonedDateTime.now(ZoneId.of("Europe/London"))
      val startDate = nowDate.minusWeeks(97).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      val endDate = nowDate.minusWeeks(9).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      val ceasedToAct = nowDate.minusWeeks(19).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      Future.successful(
        Ok(s"""
              |{
              |  "etag": "somelongstring",
              |  "cases": [
              |    {
              |      "type": "in-administration",
              |      "dates": [
              |        {
              |          "type": "administration-started-on",
              |          "date": "$startDate"
              |        },
              |        {
              |          "type": "administration-ended-on",
              |          "date": "$endDate"
              |        }
              |      ],
              |      "practitioners": [
              |        {
              |          "name": "John Smith",
              |          "address": {
              |            "address_line_1": "1 Somewhere St",
              |            "address_line_2": "Someburb",
              |            "locality": "Somewheresville",
              |            "postal_code": "SM1 2WH"
              |          },
              |          "role": "practitioner"
              |        },
              |        {
              |          "name": "James McDoe",
              |          "address": {
              |            "address_line_1": "45 Somewhere Avenue",
              |            "address_line_2": "Someburbia",
              |            "locality": "Somewheresville",
              |            "postal_code": "SM5 3WH"
              |          },
              |          "ceased_to_act_on": "$ceasedToAct",
              |          "role": "practitioner"
              |        }
              |      ],
              |      "number": "1"
              |    }
              |  ],
              |  "status": [
              |    "in-administration",
              |    "administrative-receiver"
              |  ]
              |}
              |""".stripMargin).as("application/json")
      )
    } else {
      val date = ZonedDateTime.now(ZoneId.of("Europe/London")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      Future.successful(
        NotFound(s"""
                    |{
                    |  "timestamp": "$date",
                    |  "status": 404,
                    |  "error": "Not Found",
                    |  "path": "/company/$companyNumber/insolvency"
                    |}
                    |""".stripMargin).as("application/json")
      )
    }
  }

}
