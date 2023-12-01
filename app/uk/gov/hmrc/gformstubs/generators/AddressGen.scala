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

package uk.gov.hmrc.gformstubs.generators

import uk.gov.hmrc.gformstubs.model.{ Address, InternationalAddress, UkAddress }

import scala.util.Random

trait AddressGen {
  def addressGen: Address = Random.shuffle(Seq(ukAddressGen, internationalAddressGen)).head

  def ukAddressGen: UkAddress =
    UkAddress(
      PrimitiveGen.nonEmptyAlphaNumStrGen,
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      None
    )

  def internationalAddressGen: InternationalAddress =
    InternationalAddress(
      PrimitiveGen.nonEmptyAlphaNumStrGen,
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      PrimitiveGen.internationalCountryCodeGen,
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen)
    )
}

object AddressGen extends AddressGen
