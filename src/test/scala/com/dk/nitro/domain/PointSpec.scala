package com.dk.nitro.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class PointSpec extends AnyFlatSpec with should.Matchers {

  "A Point" should "throw an exception on creation if x is negative" in {
    assertThrows[IllegalArgumentException] {
      Point(-1, 0)
    }
  }

  it should "throw an exception on creation if y is negative" in {
    assertThrows[IllegalArgumentException] {
      Point(0, -1)
    }
  }

  it should "throw an exception on creation if x and y are negative" in {
    assertThrows[IllegalArgumentException] {
      Point(-1, -1)
    }
  }

  it should "accept positive arguments" in {
    Point(1, 1).toString should be("(1, 1)")
  }

  it should "calculate distance correctly" in {
    Point(1, 2).manhattanDistance(Point(2, 3)) shouldBe 2
    Point(0, 0).manhattanDistance(Point(2, 3)) shouldBe 5
    Point(10, 10).manhattanDistance(Point(20, 30)) shouldBe 30
  }

  it should "indicate when a point exceeds bounds of another" in {
    val boundary = Point(10, 10)
    Point(20, 10).exceedsBounds(boundary) shouldBe true
    Point(10, 20).exceedsBounds(boundary) shouldBe true
    Point(10, 10).exceedsBounds(boundary) shouldBe false
  }

}
