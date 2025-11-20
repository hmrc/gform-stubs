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

import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class EmploymentDetails @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def getEmploymentDetails(nino: String, taxYear: Int) = Action.async { _ =>
    val ninoUC = nino.toUpperCase

    if (ninoUC == "AA111111A") {
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Acme",
               |      "employerReference": "123/AA1111",
               |      "employmentSequenceNumber": 1234561,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": true,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA000003D") {
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Jim",
               |      "employerReference": "123/AA1111",
               |      "employmentSequenceNumber": 1234561,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": true,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA555553D") {
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Lee",
               |      "employerReference": "013/AA5454",
               |      "employmentSequenceNumber": 1234551,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME03",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": true,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA222222A") {
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Acme",
               |      "employerReference": "123/AA1111",
               |      "employmentSequenceNumber": 1234561,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": true,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    },
               |    {
               |      "payeSchemeOperatorName": "Smith Holdings",
               |      "employerReference": "789/BB22222",
               |      "employmentSequenceNumber": 2345678,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "SMITH01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": false,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA333333A") {
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Acme",
               |      "employerReference": "123/AA1111",
               |      "employmentSequenceNumber": 1234561,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": true,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    },
               |    {
               |      "payeSchemeOperatorName": "Smith Holdings",
               |      "employerReference": "789/BB22222",
               |      "employmentSequenceNumber": 2345678,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "SMITH01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": false,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    },
               |    {
               |      "payeSchemeOperatorName": "Acme",
               |      "employerReference": "123/AA1111",
               |      "employmentSequenceNumber": 2345678,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 3456789,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME09",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": false,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA444444A") {
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Acme",
               |      "employerReference": "023/AA4444",
               |      "employmentSequenceNumber": 1234561,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": true,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    },
               |    {
               |      "payeSchemeOperatorName": "Smith Holdings",
               |      "employerReference": "009/BB4444",
               |      "employmentSequenceNumber": 2345678,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 235,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "SMITH01",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": false,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    },
               |    {
               |      "payeSchemeOperatorName": "Acme 2",
               |      "employerReference": "07/AA5555",
               |      "employmentSequenceNumber": 123456,
               |      "payeSchemeType": "Matched Employer",
               |      "employerNumber": 12345678,
               |      "payeSequenceNumber": 3456789,
               |      "employmentRecordType": "PRIMARY",
               |      "employmentStatus": "Ceased",
               |      "jobTitle": "IT Consultant",
               |      "worksNumber": "ACME09",
               |      "assignedTaxCode": "1257L",
               |      "taxCodeOperation": "Cumulative",
               |      "jobSeekersAllowance": true,
               |      "activeOccupationalPension": true,
               |      "directorIdentifier": false,
               |      "employerManualCorrespondence": true,
               |      "p161Identifier": true,
               |      "ongoingStudentLoan": true,
               |      "previousEmploymentSeqNumber": 235,
               |      "previousEmploymentStatus": "Ceased",
               |      "creationMediaType": "BACS",
               |      "employmentRecordSourceType": "ECC",
               |      "startDateSource": "ECC",
               |      "startDate": "2000-01-02",
               |      "cessationMediaType": "BACS",
               |      "empCessationRecordSourceType": "ECC",
               |      "endDateSource": "ECC",
               |      "endDate": "2023-06-27",
               |      "tisField": "00",
               |      "startingTaxCode": "1257L",
               |      "startTaxCodeOperation": "Cumulative",
               |      "modifiedScheme": true,
               |      "p46ExpatPresentCircumstance": "A",
               |      "permanentOverrideType": "0T",
               |      "payeDirection": true,
               |      "offPayrollWorker": true
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA101010A") {
      // No employments
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": []
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA202020A") {
      // Empty response
      Future.successful(
        Ok(s"""|
               |{}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA303030A") {
      // Missing employment details
      Future.successful(
        Ok(s"""|
               |{
               |  "taxYear": $taxYear,
               |  "nationalInsuranceNumber": "$ninoUC",
               |  "individualsEmploymentDetails": [
               |    {
               |      "payeSchemeOperatorName": "Acme",
               |      "employerReference": "023/AA4444",
               |      "employmentSequenceNumber": 1234561
               |    }
               |  ]
               |}
               |""".stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA500500A") {
      Future.successful(
        InternalServerError("""
                              |{
                              |   "code": "INTERNAL_SERVER_ERROR",
                              |   "reason": "The remote endpoint has indicated an internal server error"
                              |}
           """.stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA400400A") {
      Future.successful(
        BadRequest("""
                     |{
                     |   "code": "BAD_REQUEST",
                     |   "reason": "The remote endpoint has indicated a bad request"
                     |}
           """.stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA403403A") {
      Future.successful(
        Forbidden("""
                    |{
                    |   "code": "FORBIDDEN",
                    |   "reason": "The remote endpoint has indicated forbidden"
                    |}
           """.stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA422422A") {
      Future.successful(
        UnprocessableEntity("""
                              |{
                              |   "code": "UNPROCESSABLE_ENTITY",
                              |   "reason": "The remote endpoint has indicated Unprocessable Entity"
                              |}
           """.stripMargin).as("application/json")
      )
    } else if (ninoUC == "AA503503A") {
      Future.successful(
        ServiceUnavailable("""
                             |{
                             |   "code": "SERVICE_UNAVAILABLE",
                             |   "reason": "The remote endpoint has indicated Service unavailable"
                             |}
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
