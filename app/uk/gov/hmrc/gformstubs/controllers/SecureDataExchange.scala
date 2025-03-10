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

import play.api.mvc.{ AbstractController, ControllerComponents }

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

@Singleton
class SecureDataExchange @Inject() (controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def getPublicKey() = Action.async { _ =>
    Future.successful(
      Ok(
        "{\"publicKey\":\"-----BEGIN PGP PUBLIC KEY BLOCK-----\\nVersion: BCPG v1.58\\n\\nmQENBF4d4acBCAC7LjbaStGwNf3QgIPsIY8ViA1pC1CBMxd4ThIKv6FR26ugwzmK\\ng1bVaQAvWoXmEddS8kNkZZmQXFNS+y++tNM9fcl70AacCO9p0E/lsKtaCmBzVJ7z\\nWpJtFTryCgq4uXr1p5LWm6kILrryitRVG9/xpldFCOUy++gi7BiSwV5h0SroKVgm\\nh7aCYiE1dSDWPSVSs8W9F6zCBVj8WyIG0Fo+g4mUk8OWcLw4WjqszF0F2gFcXBzy\\n/Rb9gwUJNx/59p0BKWwuaHAc5okG4P6cHfLG6a2tTAYg4SYUQEpnovx8d4OYfmq8\\nGZkE6KiEM5FM0QPmgmmfCJK0MD9zkRJp3ugLABEBAAG0JlNERVMtSU5URVJOQUwg\\nPFNERVMtSU5URVJOQUxAc2Rlcy5jb20+iQEcBBABCgAGBQJeHeGnAAoJEJRngiqx\\nh7hQv6AH/R/myUJK5uOVcHF3KhxakMMMxYvaDxsZ+GXz5UyrNs1RtdZmIhzIqIqw\\nbKCNPkelQ6c7xzzMLo+k6v3rCwRywgJqguaUWA2Og0NrnDaXgIxXTMTY5663wwHd\\nHMnvpFv5ouQt0h7T8/hVoDllZLR9fldch/PPrFH7G8oYPcHx7qNs0hEl+jCIZ06g\\nWHqjA8UHu2B3uaOZKZB+FtjBFomncsbxS2BHL8WhFxk6StAWq3FnWWKWwBM4WLTg\\n9VjzmQQuGchm2KFTcIzhPhjSD8RvC4yz81DQG03xvenjeskidPRa8IJqT2F56sVL\\n4NAhEQy51I3pyKDwTn8UFgSWDsv+Ijw=\\n=w56m\\n-----END PGP PUBLIC KEY BLOCK-----\"}"
      ).as("application/json")
    )
  }
}
