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

import uk.gov.hmrc.gformstubs.model.DesRegistrationResponse

trait DesRegistrationResponseGen {
  def desRegistrationResponseGen: DesRegistrationResponse =
    DesRegistrationResponse(
      PrimitiveGen.nonEmptyAlphaNumStrGen,
      Some(PrimitiveGen.nonEmptyAlphaNumStrGen),
      PrimitiveGen.nonEmptyAlphaNumStrGen,
      PrimitiveGen.booleanGen,
      PrimitiveGen.booleanGen,
      PrimitiveGen.booleanGen,
      PrimitiveGen.booleanGen,
      DesEntityGen.desEntityGen,
      AddressGen.addressGen,
      Some(ContactDetailsGen.contactDetailsGen)
    )
}

object DesRegistrationResponseGen extends DesRegistrationResponseGen
