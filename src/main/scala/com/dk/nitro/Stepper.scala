package com.dk.nitro

import com.dk.nitro.domain.{Direction, Move, Point}

trait Stepper {
  def initialDirection: Direction
  def stepsToTake: Int
  def step(currentMove: Move, currentPoint: Point): (Move, Point)
}
