package cafe

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec


class AttemptTwoSpec extends AnyWordSpec with Matchers {

  "bill" should {
    "return a message vb" in {
      AttemptTwo.bill(List.empty, 0) mustBe 0
    }
    "return a error" in {

    }
  }
}
