/*
 * Copyright 2019 HM Revenue & Customs
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

package uk.gov.hmrc.gformstubs

import org.scalatest.matchers.{ MatchResult, Matcher }
import play.api.libs.json._

trait JsResultMatcher {
  def hasJsSuccess[E](element: E): Matcher[JsResult[E]] = new HasJsSuccess[E](element)
  def beJsSuccess[E]: Matcher[JsResult[E]] = new BeJsSuccess[E]
}

final private class BeJsSuccess[E] extends Matcher[JsResult[E]] {
  def apply(jsResult: JsResult[E]): MatchResult =
    MatchResult(
      jsResult.fold(_ => false, _ => true),
      s"'$jsResult' was not JsSuccess.",
      s"'$jsResult' was JsSuccess, but it should be JsError."
    )
}

final private class HasJsSuccess[E](element: E) extends Matcher[JsResult[E]] {
  def apply(jsResult: JsResult[E]): MatchResult =
    MatchResult(
      jsResult.fold(_ => false, _ == element),
      s"'$jsResult' did not contain an element matching '$element'.",
      s"'$jsResult' contained an element matching '$element', but should not have."
    )
}
