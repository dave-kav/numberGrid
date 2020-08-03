package com.dk.nitro.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MoveSpec extends AnyFlatSpec with should.Matchers {

  "Moves.left" should "produce expected new Point" in {
    Step.left(Point(1, 1)) shouldBe Point(0, 1)
  }

  "Moves.right" should "produce expected new Point" in {
    Step.right(Point(1, 1)) shouldBe Point(2, 1)
  }

  "Moves.up" should "produce expected new Point" in {
    Step.up(Point(1, 1)) shouldBe Point(1, 0)
  }

  "Moves.down" should "produce expected new Point" in {
    Step.down(Point(1, 1)) shouldBe Point(1, 2)
  }
}
