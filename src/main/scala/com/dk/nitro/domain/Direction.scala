package com.dk.nitro.domain

sealed trait Direction {
  def next: Direction
}

object Direction {
  case object Left extends Direction {
    override def next: Direction = Down
  }
  case object Down extends Direction {
    override def next: Direction = Right
  }
  case object Right extends Direction {
    override def next: Direction = Up
  }
  case object Up extends Direction {
    override def next: Direction = Left
  }
}

object Step {
  type Step = Point => Point
  val right: Step = (p: Point) => p.copy(x = p.x + 1)
  val left:  Step = (p: Point) => p.copy(x = p.x - 1)
  val down:  Step = (p: Point) => p.copy(y = p.y + 1)
  val up:    Step = (p: Point) => p.copy(y = p.y - 1)

  def apply(direction: Direction): Step = direction match {
    case Direction.Left   => left
    case Direction.Down   => down
    case Direction.Right  => right
    case Direction.Up     => up
  }
}

case class Move(direction: Direction, steps: Int)
