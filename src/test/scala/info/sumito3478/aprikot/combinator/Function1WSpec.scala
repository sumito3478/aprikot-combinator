/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.sumito3478.aprikot.combinator

import org.scalatest
import scalatest.FunSpec

class Function1WSpec extends FunSpec {
  describe("|<|") {
    it("""should pass the result of the right side to the function on the left
        |  side""".stripMargin) {
      val abs = math.abs _
      val i = abs |<| -1
      assert(i === 1)
    }
  }

  describe("`|>|` and `|<|`") {
    it("should be able to be composed") {
      val min = math.min _
      val max = math.max _
      val operatorRet = 1 |>| min |<| 4 |>| max |<| 6 |>| min |<| 7
      val funcCallRet = min(max(min(1, 4), 6), 7)
      assert(operatorRet === funcCallRet)
    }
  }

  describe("|*|") {
    it("S-combinates the functions") {
      val cos = math.cos _
      val max = math.max(_: Double, _: Double)
      val cosSmax = cos |*| max // Returns the max of x and cos(x)!
      assert(cosSmax(0.3) === max(0.3, cos(0.3)))
    }
  }
}