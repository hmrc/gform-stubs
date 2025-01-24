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

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers.{ contentAsString, defaultAwaitTimeout, status, stubControllerComponents }

class AgentAccessControlSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  val fakeRequest = FakeRequest("GET", "/")

  "Agent Access Controller" should {
    "return 200 for authorise(valid auth type, agent code and client id)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("mtd-vat-auth", "AARN000001", "100000001")(fakeRequest)
      status(result) shouldBe Status.OK
    }

    "return 401 for authorise(valid auth type, invalid agent code and client id)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("mtd-vat-auth", "XARN000001", "100000001")(fakeRequest)
      status(result) shouldBe Status.UNAUTHORIZED
      contentAsString(result) shouldBe "NO_RELATIONSHIP"
    }

    "return 401 for authorise(valid auth type, agent code but not authorised for client id)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("mtd-vat-auth", "AARN000001", "100000002")(fakeRequest)
      status(result) shouldBe Status.UNAUTHORIZED
      contentAsString(result) shouldBe "NO_ASSIGNMENT"
    }

    "return 403 for authorise(valid auth type, non-authorised agent code)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("mtd-vat-auth", "FARN000001", "100000002")(fakeRequest)
      status(result) shouldBe Status.FORBIDDEN
    }

    "return 200 for authorise(valid epaye-auth type, agent code and EmpRef)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("epaye-auth", "AARN000001", "TN000001%2F123456")(fakeRequest)
      status(result) shouldBe Status.OK
    }

    "return 401 for authorise(valid epaye-auth type, agent code and EmpRef)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("epaye-auth", "AARN000001", "TN000002%2F123456")(fakeRequest)
      status(result) shouldBe Status.UNAUTHORIZED
      contentAsString(result) shouldBe "NO_ASSIGNMENT"
    }

    "return 401 for authorise(valid epaye-auth type, agent code with no relationship to EmpRef)" in {
      val controller = new AgentAccessControl(stubControllerComponents())
      val result = controller.authorise("epaye-auth", "AARN000001", "TN000003%2F123456")(fakeRequest)
      status(result) shouldBe Status.UNAUTHORIZED
      contentAsString(result) shouldBe "NO_RELATIONSHIP"
    }

  }
}
