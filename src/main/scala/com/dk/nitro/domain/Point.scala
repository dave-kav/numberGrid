package com.dk.nitro.domain

case class Point(x: Int, y: Int) {
  require(x >= 0 && y >= 0, "Coordinates can't be negative")

  def exceedsBounds(other: Point): Boolean = this.x > other.x || this.y > other.y

  def manhattanDistance(other: Point): Int = {
    math.abs(this.x - other.x) + math.abs(this.y - other.y)
  }

  override def toString: String = s"($x, $y)"
}
