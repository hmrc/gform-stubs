/*
 * Copyright 2020 HM Revenue & Customs
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

import gigahorse.{ ContentTypes, HttpWrite, MimeTypes }
import gigahorse.support.okhttp.Gigahorse

import java.nio.charset.Charset
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import play.api.libs.json._

import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import uk.gov.hmrc.gformstubs.JsResultMatcher
import uk.gov.hmrc.gformstubs.model._

class RegistrationResponseSpec
    extends WordSpec with Matchers with JsResultMatcher with ScalaFutures with ScalaCheckPropertyChecks {

  override implicit val patienceConfig = PatienceConfig(timeout = 3 seconds, interval = 500 millis)

  val utf8 = Charset.forName("UTF-8")

  implicit val write: HttpWrite[DesRegistrationRequest] = new HttpWrite[DesRegistrationRequest] {
    def contentType: Option[String] = Some(ContentTypes.JSON)
    def toByteArray(drr: DesRegistrationRequest): Array[Byte] = Json.stringify(Json.toJson(drr)).getBytes(utf8)
  }

  val payloadRequest = DesRegistrationRequest("ITSA", false, false)

  val reads = (payload: String) => implicitly[Reads[DesRegistrationResponse]].reads(Json.parse(payload))

  List(
    "5119999953",
    "5374039942",
    "5399763152",
    "1695876755",
    "8320008777",
    "4300555796",
    "2300555797",
    "9300555799",
    "9300555804",
    "4234466847",
    "3125243009",
    "4758614077",
    "1695877354",
    "3695874453",
    "2695878593",
    "2695878609",
    "2695878835",
    "9948824679",
    "2695878979",
    "2656920898",
    "2630076230",
    "6047802839",
    "6070976552",
    "2648123999",
    "2698623554",
    "3570093255",
    "3201049788",
    "1111111111",
    "6044205975",
    "2234020489",
    "8089302668",
    "5774004485",
    "3596467756",
    "7741001433",
    "2695871143",
    "1101273329",
    "6392072470",
    "8943546273",
    "9958034004",
    "1849887835",
    "1760007220",
    "4328071779",
    "5393654830",
    "4116383174",
    "4142781267",
    "4000213004",
    "7000669635",
    "2807039844",
    "5865491691",
    "4111111129",
    "1167098481",
    "1367669232",
    "2681265194",
    "2775878923",
    "9258745218",
    "5158745341",
    "1027255796",
    "1308066039",
    "2158745254",
    "8100226664",
    "8112236888",
    "7100226656",
    "7840249863",
    "1178303802",
    "3123456791",
    "7928657763",
    "7316681524",
    "6762198553",
    "8759789785",
    "1112236968",
    "8761718639",
    "8152321601",
    "9446008106",
    "1144440225",
    "2400256374",
    "1144440367",
    "1144440368",
    // "2112236914", {"code": "SERVICE_UNAVAILABLE", "reason": "Dependent systems are currently not responding" }
    "2571008248",
    "7158745340",
    "4000010682",
    "5000019549",
    "9000010802",
    "9833918156",
    "8186064496",
    "1741654440",
    "2460995502",
    "6454326466",
    "1284457836",
    "7956000246",
    "1151018764",
    "1370015155",
    "1631324054",
    "1681016287",
    "3023827827",
    "3696003594",
    "4684018965",
    "5909383949",
    "5994055693",
    "7487130932",
    "8759044878",
    "9222437564",
    "9264374455",
    "2119999963",
    "2222222222",
    "2119999950",
    "9119999951"
  ).map { utr =>
    "Successfully parse payload from" should {

      s"des/registration/individual/utr/$utr (expect flakiness since we run on QA)" ignore {

        Gigahorse.withHttp(Gigahorse.config) { http =>
          val request =
            Gigahorse
              .url(s"https://admin.qa.tax.service.gov.uk/des/registration/individual/utr/$utr")
              .post(payloadRequest)
              .withContentType(MimeTypes.JSON, Gigahorse.utf8)
              .addHeaders(
                "Authorization" -> "Bearer EvYRlYH8AS_hZGw32ffqJ25Mz04a",
                "Environment"   -> "ist0"
              )

          val resultF: Future[String] = http.run(request, Gigahorse.asString)

          resultF
            .map(reads)
            .futureValue should beJsSuccess
        }
      }
    }
  }
}
