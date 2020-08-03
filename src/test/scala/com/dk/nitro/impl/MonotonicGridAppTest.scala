package com.dk.nitro.impl

import com.dk.nitro.IO
import com.dk.nitro.domain.{Choice, Grid, Point, Quit}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.mutable

class MonotonicGridAppTest extends AnyFlatSpec with should.Matchers with BeforeAndAfterEach {

  val testIO: TestIO = new TestIO
  val testApp: MonotonicGridApp = new MonotonicGridApp(testIO) {
    override def main(choice: Choice): Unit = {}
    override protected def askChoice(): Choice = Quit
  }

  override def beforeEach(): Unit = {
    testIO.outputLines.clear()
    super.beforeEach()
  }

  "MonotonicGridApp" should "print welcome message" in {
    testApp.run()

    withClue(testIO.outputLines) {
      testIO.outputLines.head shouldBe "################"
      testIO.outputLines(1) shouldBe "### Welcome ####"
      testIO.outputLines(2) shouldBe "################"
    }
  }

  it should "print options" in {
    testApp.run()

    withClue(testIO.outputLines) {
      testIO.outputLines.filter(_.contains("Options:"))
      testIO.outputLines.filter(_.contains("1) Find distance of 'X' from origin"))
      testIO.outputLines.filter(_.contains("2) Quit"))
      testIO.outputLines.filter(_.contains("Selection:"))
    }
  }

  it should "print details" in {
    val cells = Seq(
      Point(1, 1), Point(2, 1), Point(2, 0),
      Point(1, 0), Point(0, 0), Point(0, 1),
      Point(0, 2), Point(1, 2), Point(2, 2),
    )

    testApp.printDetails(Grid(cells, 3), 2)

    withClue(testIO.outputLines) {
      testIO.outputLines.filter(_.contains("Details of interest:"))
      testIO.outputLines.filter(_.contains("Location of origin: (1, 1)"))
      testIO.outputLines.filter(_.contains("Location of 2: (2, 1)"))
      testIO.outputLines.filter(_.contains("Manhattan distance of 2 from origin: 2"))
    }
  }

}

class TestIO extends IO {

  val outputLines = mutable.ListBuffer[String]()

  override def inputLine: String = ""
  override def inputInt: Int = 1
  override def output(message: String): Unit = outputLines += message
  override def outputLn(message: String): Unit = outputLines += message
  override def error(message: String): Unit = outputLines += s"ERROR: $message"
  override def blankLine(): Unit = { }
}