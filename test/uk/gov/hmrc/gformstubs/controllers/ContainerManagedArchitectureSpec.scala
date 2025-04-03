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
import play.api.libs.json.JsDefined
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{ contentAsJson, contentType, defaultAwaitTimeout, status, stubControllerComponents }

import scala.concurrent.Future

class ContainerManagedArchitectureSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {
  val fakeRequest = FakeRequest("POST", "/")

  "postVatParcelsC2C" should {
    "return 200 with simple response" in {
      val controller: ContainerManagedArchitecture = new ContainerManagedArchitecture(stubControllerComponents())
      val result: Future[Result] = controller.postVatParcelsC2C()(fakeRequest)
      status(result) shouldBe Status.OK
      contentType(result) shouldBe Some("application/json")
      (contentAsJson(result) \ "success" \ "processingDate") match {
        case JsDefined(_) => assert(true)
        case _            => assert(false)
      }
    }
  }
}
