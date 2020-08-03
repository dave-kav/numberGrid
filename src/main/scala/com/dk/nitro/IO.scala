package com.dk.nitro

trait IO {
  def inputLine: String
  def inputInt: Int
  def output(message: String): Unit
  def outputLn(message: String): Unit
  def error(message: String): Unit
  def blankLine(): Unit
}
