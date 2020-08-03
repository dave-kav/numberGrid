libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % Test

ThisBuild / organization := "com.dk.nitro"
ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version      := "0.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "nitro"
  )