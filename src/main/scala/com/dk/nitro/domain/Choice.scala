package com.dk.nitro.domain

trait Choice
case object Quit    extends Choice
case object Play    extends Choice
case object Default extends Choice
