package com.dk.nitro.domain

import com.dk.nitro.Stepper

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

case class Grid(cells: Seq[Point], size: Int) {
  val origin: Point = cells.head
  def locationOf(index: Int): Point = cells(index - 1)
  def distanceToOrigin(index: Int): Int = origin.manhattanDistance(locationOf(index))
}

class GridBuilder(stepper: Stepper) {

  def ofSize(size: Int): Grid = {
    require(size > 0, "Grid's gotta be bigger that that!")
    require(size % 2 == 1, "Grid length must be odd, please!")
    require(size < 999, "That's too big!")

    val middleIndex = size / 2
    val origin = Point(middleIndex, middleIndex)

    Grid(fillGrid(origin, size, ListBuffer(origin)).toSeq, size)
  }

  private def fillGrid(origin: Point, gridSize: Int, points: ListBuffer[Point]): ListBuffer[Point] = {
    val endPoint = Point(gridSize - 1, gridSize - 1)

    @tailrec
    def move(currentMove: Move, points: ListBuffer[Point]): ListBuffer[Point] = {
      val currentPoint = points.last
      if (currentPoint == endPoint) points
      else {
        val (nextMove, nextPoint) = stepper.step(currentMove, currentPoint)
        if (nextPoint.exceedsBounds(endPoint)) throw new RuntimeException(s"nextPoint $nextPoint exceeds boundary $endPoint")
        move(nextMove, points += nextPoint)
      }
    }

    move(Move(stepper.initialDirection, stepper.stepsToTake), points)
  }

}
