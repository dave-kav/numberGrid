package com.dk.nitro

import com.dk.nitro.domain.{Choice, GridBuilder}

trait GridApp {
  def run(): Unit
  def main(choice: Choice): Unit
  def play(gridBuilder: GridBuilder): Unit
  def exit(): Unit
}
