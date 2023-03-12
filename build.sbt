ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "io.github.adammansson"

lazy val root = (project in file("."))
  .aggregate(result, register)

lazy val common = (project in file("common"))

lazy val result = (project in file("result"))
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  )
  .dependsOn(common)

lazy val register = (project in file("register"))
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  )
  .dependsOn(common)
