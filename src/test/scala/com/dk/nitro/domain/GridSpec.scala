package com.dk.nitro.domain

import com.dk.nitro.impl.CounterClockWiseStepper
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class GridSpec extends AnyFlatSpec with should.Matchers {

  val size3Grid: Grid = new GridBuilder(new CounterClockWiseStepper()).ofSize(3)
  val size5Grid: Grid = new GridBuilder(new CounterClockWiseStepper()).ofSize(5)

  "A Grid of size 3" should "have origin of (1, 1)" in {
    size3Grid.origin shouldBe Point(1, 1)
  }

  "A Grid of size 3" should "have 9 cells" in {
    size3Grid.cells.size shouldBe 9
  }

  "A Grid of size 3" should "have correct sequence" in {
    size3Grid.cells shouldBe
      Seq(
        Point(1, 1), Point(2, 1), Point(2, 0),
        Point(1, 0), Point(0, 0), Point(0, 1),
        Point(0, 2), Point(1, 2), Point(2, 2),
      )
  }

  "Locations" should "be as expected" in {
    size3Grid.locationOf(3) shouldBe Point(2, 0)
    size3Grid.locationOf(5) shouldBe Point(0, 0)
    size3Grid.locationOf(9) shouldBe Point(2, 2)
  }

  // Test cases from brief
  "Location 12" should "be at (4, 1)" in {
    size5Grid.locationOf(12) shouldBe Point(4, 1)
  }

  "A grid of size 5" should "have correct sequence" in {
    size5Grid.toString shouldBe
      "Grid(List(" +
        "(2, 2), (3, 2), (3, 1), (2, 1), (1, 1), " +
        "(1, 2), (1, 3), (2, 3), (3, 3), (4, 3), " +
        "(4, 2), (4, 1), (4, 0), (3, 0), (2, 0), " +
        "(1, 0), (0, 0), (0, 1), (0, 2), (0, 3), " +
        "(0, 4), (1, 4), (2, 4), (3, 4), (4, 4)" +
      "),5)"
  }

  "Location 1" should "be distance of 0 from origin" in {
    size5Grid.distanceToOrigin(1) shouldBe 0
  }

  "Location 12" should "be distance of 3 from origin" in {
    size5Grid.distanceToOrigin(12) shouldBe 3
  }

  "Location 23" should "be at (2, 4)" in {
    size5Grid.locationOf(23) shouldBe Point(2, 4)
  }

  "Location 23" should "be distance of 2 from origin" in {
    size5Grid.distanceToOrigin(23) shouldBe 2
  }

  "Location 1024" should "be distance of 31 from origin" in {
    new GridBuilder(new CounterClockWiseStepper()).ofSize(33).distanceToOrigin(1024) shouldBe 31
  }

  "Location 368078" should "be distance 371 from origin" in {
    new GridBuilder(new CounterClockWiseStepper()).ofSize(611).distanceToOrigin(368078) shouldBe 371
  }
}
