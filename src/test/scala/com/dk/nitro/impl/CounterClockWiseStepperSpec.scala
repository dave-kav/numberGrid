package com.dk.nitro.impl

import com.dk.nitro.Stepper
import com.dk.nitro.domain.{Direction, Move, Point}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class CounterClockWiseStepperSpec extends AnyFlatSpec with should.Matchers {

  val stepper: Stepper = new CounterClockWiseStepper()

  "A Stepper" should "return expected values for next step" in {
    stepper.step(Move(Direction.Right, 1), Point(1, 1))._1 shouldBe Move(Direction.Up,    1)
    stepper.step(Move(Direction.Right, 2), Point(1, 1))._1 shouldBe Move(Direction.Right, 1)

    stepper.step(Move(Direction.Up,    1), Point(1, 1))._1 shouldBe Move(Direction.Left,  2)
    stepper.step(Move(Direction.Up,    2), Point(1, 1))._1 shouldBe Move(Direction.Up,    1)

    stepper.step(Move(Direction.Left,  1), Point(1, 1))._1 shouldBe Move(Direction.Down,  2)
    stepper.step(Move(Direction.Left,  2), Point(1, 1))._1 shouldBe Move(Direction.Left,  1)

    stepper.step(Move(Direction.Down,  1), Point(1, 1))._1 shouldBe Move(Direction.Right, 3)
    stepper.step(Move(Direction.Down,  2), Point(1, 1))._1 shouldBe Move(Direction.Down,  1)
  }

  "A Stepper" should "return expected values for next point" in {
    stepper.step(Move(Direction.Right, 1), Point(1, 1))._2 shouldBe Point(2, 1)
    stepper.step(Move(Direction.Up,    1), Point(1, 1))._2 shouldBe Point(1, 0)
    stepper.step(Move(Direction.Left,  1), Point(1, 1))._2 shouldBe Point(0, 1)
    stepper.step(Move(Direction.Down,  1), Point(1, 1))._2 shouldBe Point(1, 2)
  }

}
