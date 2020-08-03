package com.dk.nitro.impl

import com.dk.nitro.Stepper
import com.dk.nitro.domain.{Direction, Move, Point, Step}

class CounterClockWiseStepper(var stepsToTake: Int = 1) extends Stepper {

  override def initialDirection: Direction = Direction.Right

  override def step(currentMove: Move, currentPoint: Point): (Move, Point) = {
    if (currentMove.steps > 1) takeStep(currentMove, currentPoint, currentMove.direction)
    else currentMove.direction match {
      case d: Direction if d == Direction.Right || d == Direction.Left
                        =>  turn(currentPoint, d)
      case d: Direction =>  stepsToTake += 1
                            turn(currentPoint, d)
    }
  }

  private def turn(currentPoint: Point, direction: Direction): (Move, Point) = {
    (Move(direction.next, stepsToTake), Step(direction)(currentPoint))
  }

  private def takeStep(currentMove: Move, currentPoint: Point, direction: Direction): (Move, Point) = {
    (Move(direction, currentMove.steps - 1), Step(direction)(currentPoint))
  }
}
