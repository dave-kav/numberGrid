package com.dk.nitro.impl

import com.dk.nitro.domain._
import com.dk.nitro.implicits.ChoiceConverter._
import com.dk.nitro.{GridApp, IO}

import scala.util.{Failure, Success, Try}

class MonotonicGridApp(io: IO) extends GridApp {

  override def run(): Unit = {
    io.blankLine()
    welcome()
    io.blankLine()
    main(askChoice())
  }

  override def main(choice: Choice): Unit = {
    def repeat(): Unit = main(askChoice())

    choice match {
      case Quit => exit()
      case Play => play(); repeat()
      case _    => invalidChoice(); repeat()
    }
  }

  override def play(gridBuilder: GridBuilder = new GridBuilder(new CounterClockWiseStepper)): Unit = {
    io.output("Enter a grid size: ")
    val gridSize = io.inputInt
    io.outputLn(s"Building grid of size $gridSize...")

    Try(gridBuilder.ofSize(gridSize)) match {
      case Failure(exception) => io.error(exception.getMessage); play()
      case Success(grid)      => gridLoop(grid)
    }
  }

  override def exit(): Unit = {
    io.outputLn("Goodbye!")
    sys.exit()
  }

  protected def welcome(): Unit = {
    io.outputLn("################")
    io.outputLn("### Welcome ####")
    io.outputLn("################")
  }

  protected def askChoice(): Choice = {
    val menu = new StringBuilder()
      .append("Options:").append("\n")
      .append("1) Find distance of 'X' from origin").append("\n")
      .append("2) Quit").append("\n")

    io.blankLine()
    io.outputLn(menu.toString())
    io.output("Selection: ")
    io.inputInt.toChoice
  }

  @scala.annotation.tailrec
  private def gridLoop(grid: => Grid): Unit = {
    io.output("Enter a location (or q to quit to menu): ")
    val locationLine = io.inputLine

    if (locationLine != "q") {
      Try(locationLine.toInt) match {
        case Failure(exception) => io.outputLn("Invalid selection!")
        case Success(location) =>
          if (location < 1 || location > grid.cells.size) {
            io.error(s"$location is out of bounds! (1 - ${grid.cells.size})")
          } else printDetails(grid, location)
      }

      gridLoop(grid)
    }
  }

  private[impl] def printDetails(grid: => Grid, location: Int): Unit = {
    io.outputLn("Details of interest:")
    io.outputLn(s"\tLocation of origin: ${grid.origin}")
    io.outputLn(s"\tLocation of $location: ${grid.locationOf(location)}")
    io.outputLn(s"\tManhattan distance of $location from origin: ${grid.distanceToOrigin(location)}")
    io.blankLine()
  }

  private[impl] def invalidChoice(): Unit = {
    io.outputLn("Invalid Selection!")
    io.outputLn("")
  }
}

object CliApp extends App {
  val io = new ConsoleIO()
  io.outputLn("Loading app...")
  new MonotonicGridApp(io).run()
}
