package com.dk.nitro.implicits

import com.dk.nitro.domain.{Choice, Default, Play, Quit}

object ChoiceConverter {
  implicit class OperationChoice(val value: Int) extends AnyVal {
    def toChoice: Choice = {
      if (value == 1) Play
      else if (value == 2) Quit
      else Default
    }
  }
}
