package com.dk.nitro.impl

import com.dk.nitro.IO

import scala.util.Try

class ConsoleIO extends IO {
  override def inputLine: String = scala.io.StdIn.readLine()
  override def inputInt: Int = Try(scala.io.StdIn.readInt()).getOrElse(0)
  override def output(message: String): Unit = print(message)
  override def outputLn(message: String): Unit = println(message)
  override def error(message: String): Unit = println(s"ERROR: $message")
  override def blankLine(): Unit = println("")
}
